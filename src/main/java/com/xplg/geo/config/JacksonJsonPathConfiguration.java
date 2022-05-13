package com.xplg.geo.config;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

import java.util.EnumSet;
import java.util.Set;

public class JacksonJsonPathConfiguration implements Configuration.Defaults {
    private final JsonProvider jsonProvider = new JacksonJsonProvider();
    private final MappingProvider mappingProvider = new JacksonMappingProvider();
    private final Set<Option> options = EnumSet.noneOf(Option.class);

    private static final JacksonJsonPathConfiguration instance = new JacksonJsonPathConfiguration();

    private JacksonJsonPathConfiguration() {
        //
    }

    @Override
    public JsonProvider jsonProvider() {
        return jsonProvider;
    }

    @Override
    public Set<Option> options() {
        return options;
    }

    @Override
    public MappingProvider mappingProvider() {
        return mappingProvider;
    }

    public static JacksonJsonPathConfiguration getInstance() {
        return instance;
    }
}
