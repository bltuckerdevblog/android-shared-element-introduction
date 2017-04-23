package com.bltucker.transitiontutorial.glide;


import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;

public class SvgDecoder implements ResourceDecoder<InputStream, SVG> {
    @Override
    public Resource<SVG> decode(InputStream source, int width, int height) throws IOException {
        try{
            SVG svg = SVG.getFromInputStream(source);
            return new SimpleResource<SVG>(svg);
        } catch(SVGParseException ex){
            Timber.e(ex, "Error loading svg");
            throw new IOException("Error loading svg", ex);
        }
    }

    @Override
    public String getId() {
        return "";
    }
}
