package com.bltucker.transitiontutorial.glide;


import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;

public class SvgSoftwareLayerSetter implements RequestListener<Uri, PictureDrawable> {

    @Override
    public boolean onException(Exception e, Uri model, Target<PictureDrawable> target, boolean isFirstResource) {
        ImageView imageView = ((ImageViewTarget<?>) target).getView();
        imageView.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null);
        return false;
    }

    @Override
    public boolean onResourceReady(PictureDrawable resource, Uri model, Target<PictureDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
        ImageView imageView = ((ImageViewTarget<?>) target).getView();
        imageView.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null);
        return false;
    }
}
