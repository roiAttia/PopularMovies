package roiattia.com.newpopularmovies.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import roiattia.com.newpopularmovies.R;
import roiattia.com.newpopularmovies.models.Trailers;
import roiattia.com.newpopularmovies.utils.ConstantsUtil;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersAdapterViewHolder> {

    private List<Trailers> mTrailers;
    private Context mContext;
    private final TrailersAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface TrailersAdapterOnClickHandler {
        void onClick(int trailerIndex);
    }

    public TrailersAdapter(Context context, TrailersAdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }


    @Override
    public TrailersAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.list_item_trailer;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new TrailersAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailersAdapterViewHolder holder, int position) {
        String thumbnailPath = mTrailers.get(position).key();
        String imageUrl = ConstantsUtil.YOUTUBE_THUMBNAIL_PATH
                + thumbnailPath
                + ConstantsUtil.YOUTUBE_THUMBNAIL_PATH_END;

        Picasso.with(mContext)
                .load(imageUrl)
                .fit()
                .into(holder.trailerThumbnail);
    }

    @Override
    public int getItemCount() {
        return mTrailers == null ? 0 : mTrailers.size();
    }

    public class TrailersAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView trailerThumbnail;

        TrailersAdapterViewHolder(View itemView) {
            super(itemView);
            trailerThumbnail = itemView.findViewById(R.id.iv_trailer_thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }
    }

    /**
     * Sets the movies data to mMovieList
     * @param trailers the movies list to set
     */
    public void setTrailersData(List<Trailers> trailers) {
        mTrailers = trailers;
        notifyDataSetChanged();
    }
}
