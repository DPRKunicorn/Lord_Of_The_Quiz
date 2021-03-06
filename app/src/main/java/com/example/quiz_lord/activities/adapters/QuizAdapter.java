package com.example.quiz_lord.activities.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_lord.gamelogic.Quiz;
import com.example.quiz_lord.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizHolder>
{
    private int page = 0;
    private Quiz[] quizzes;
    public QuizAdapter()
    {
        try
        {
            Quiz.pullQuizzes(0);
            quizzes= Quiz.getQuizzes();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            quizzes = new Quiz[0];
        }

    }
    public class QuizHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public QuizHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    public int getPage()
    {
        return page;
    }

    public void nextPage()
    {
        page++;
        try
        {
            Quiz.pullQuizzes(page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_quiz, parent, false);
        QuizHolder vh = new QuizHolder(v);
        return vh;
    }
    @Override
    public int getItemCount()
    {
        return quizzes.length;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizHolder holder, int position)
    {
        Quiz quiz=quizzes[position];
        String name = quiz.getName();
        String author = quiz.getAuthor();
        Bitmap thumbnail = quiz.getThumbnail();
        ImageView iv = holder.cardView.findViewById(R.id.quiz_card_background);
        iv.setTag(position);
        TextView nameView = holder.cardView.findViewById(R.id.quiz_card_name_textView);
        TextView authorView = holder.cardView.findViewById(R.id.quiz_card_author_textView);
        nameView.setText(name);
        authorView.setText(author);
        iv.setImageBitmap(thumbnail);
    }
}