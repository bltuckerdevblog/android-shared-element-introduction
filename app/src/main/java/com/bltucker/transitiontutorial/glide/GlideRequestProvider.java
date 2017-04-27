package com.bltucker.transitiontutorial.glide;


import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;

import com.bltucker.transitiontutorial.glide.SvgDecoder;
import com.bltucker.transitiontutorial.glide.SvgDrawableTranscoder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GlideRequestProvider {

    private final SvgDecoder svgDecoder;
    private final FileToStreamDecoder<SVG> fileToStreamDecoder;
    private final StreamEncoder streamEncoder;
    private final SvgDrawableTranscoder svgDrawableTranscoder;

    @Inject
    public GlideRequestProvider(SvgDecoder svgDecoder,
                                FileToStreamDecoder<SVG> fileToStreamDecoder,
                                StreamEncoder streamEncoder,
                                SvgDrawableTranscoder svgDrawableTranscoder){
        this.svgDecoder = svgDecoder;
        this.fileToStreamDecoder = fileToStreamDecoder;
        this.streamEncoder = streamEncoder;
        this.svgDrawableTranscoder = svgDrawableTranscoder;
    }


    public GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> getGenericRequest(Context context){
            return Glide.with(context)
            .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
            .from(Uri.class)
            .as(SVG.class)
            .transcode(svgDrawableTranscoder, PictureDrawable.class)
            .sourceEncoder(streamEncoder)
            .cacheDecoder(fileToStreamDecoder)
            .decoder(svgDecoder);
    }

}
