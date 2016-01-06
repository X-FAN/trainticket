package com.xf.sherlock.convertfactory;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

final class StringResponseBodyConverter implements Converter<ResponseBody, String> {


    @Override
    public String convert(ResponseBody value) throws IOException {

        return value.string();
    }
}
