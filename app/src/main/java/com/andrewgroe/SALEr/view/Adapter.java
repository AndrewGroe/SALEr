package com.andrewgroe.SALEr.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewgroe.SALEr.R;
import com.andrewgroe.SALEr.model.children.Children;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    private Context mContext;
    private ArrayList<Children> mData;


    Adapter(Context mContext, ArrayList<Children> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item, viewGroup, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {

        myViewHolder.postTitle.setText(mData.get(i).getData().getTitle());
        myViewHolder.postDomain.setText(mData.get(i).getData().getDomain());
        myViewHolder.postAuthor.setText("u/ " + mData.get(i).getData().getAuthor());
        myViewHolder.postScore.setText("score: " + String.valueOf(mData.get(i).getData().getScore()));
        myViewHolder.commentCount.setText(String.valueOf(mData.get(i).getData().getScore()));
        String createdTime = EpochToDate(mData.get(i).getData().getCreated(), "M/d/yy, h:mm a");
        myViewHolder.created.setText(createdTime);

        final String url = mData.get(i).getData().getUrl();
        final String permalink = mData.get(i).getData().getPermalink();

        // Whole Row Listener
        myViewHolder.mRowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                mContext.startActivity(intent);
            }
        });

        // Comment Button Listener
        myViewHolder.commentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.reddit.com" + permalink));
                mContext.startActivity(intent);
            }
        });

    }

    private static String EpochToDate(long time, String formatString) {
        time *= 1000;
        SimpleDateFormat format = new SimpleDateFormat(formatString, Locale.getDefault());
        return format.format(new Date(time));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView postTitle, postAuthor, postDomain, postScore, commentCount, created;
        ImageView commentsButton;
        View mRowItem;

        myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            mRowItem = itemView;
            created = itemView.findViewById(R.id.created_textView);
            postTitle = itemView.findViewById(R.id.title_textView);
            postAuthor = itemView.findViewById(R.id.author_textView);
            postDomain = itemView.findViewById(R.id.domain_textView);
            postScore = itemView.findViewById(R.id.score_textView);
            commentCount = itemView.findViewById(R.id.comment_count_textView);
            commentsButton = itemView.findViewById(R.id.comments_button);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

        }
    }
}

