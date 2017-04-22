package mobi.mtech.beatfulvibes.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mtech.library.manager.CoreNetworkManager;
import com.mtech.library.util.GsonRequest;

import org.json.JSONObject;

import mobi.mtech.beatfulvibes.constant.ApiConstant;
import mobi.mtech.beatfulvibes.model.Artist;
import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.model.wrapper.ArtistWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.RelatedWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.ShuffleWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.browse.BrowseDataWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.browse.BrowseWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.discover.DiscoverWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.discover.DiscoverDataWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.mixes.MixesDataWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.mixes.MixesWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.search.SearchDataWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.search.SearchWrapper;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class NetworkManager extends CoreNetworkManager {

    public static void discover(Context context, JSONObject params, final Listener<DiscoverDataWrapper> listener) {
        String url = ApiConstant.DISCOVER;
        GsonRequest<DiscoverWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, DiscoverWrapper.class, new Response.Listener<DiscoverWrapper>() {
            @Override
            public void onResponse(DiscoverWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }
    public static void browse(Context context, JSONObject params, final Listener<BrowseDataWrapper> listener) {
        String url = ApiConstant.BROWSE;
        GsonRequest<BrowseWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, BrowseWrapper.class, new Response.Listener<BrowseWrapper>() {
            @Override
            public void onResponse(BrowseWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }
    public static void search(Context context, JSONObject params, final Listener<SearchDataWrapper> listener) {
        String url = ApiConstant.SEARCH;
        GsonRequest<SearchWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, SearchWrapper.class, new Response.Listener<SearchWrapper>() {
            @Override
            public void onResponse(SearchWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }
    public static void artists(Context context, JSONObject params, final Listener<Artist[]> listener) {
        String url = ApiConstant.ARTISTS;
        GsonRequest<ArtistWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, ArtistWrapper.class, new Response.Listener<ArtistWrapper>() {
            @Override
            public void onResponse(ArtistWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }
    public static void mixes(Context context, JSONObject params, final Listener<MixesDataWrapper> listener) {
        String url = ApiConstant.MIXES;
        GsonRequest<MixesWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, MixesWrapper.class, new Response.Listener<MixesWrapper>() {
            @Override
            public void onResponse(MixesWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }
    public static void shuffle(Context context, JSONObject params, final Listener<Track[]> listener) {
        String url = ApiConstant.SHUFFLE;
        GsonRequest<ShuffleWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, ShuffleWrapper.class, new Response.Listener<ShuffleWrapper>() {
            @Override
            public void onResponse(ShuffleWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }
    public static void related(Context context, JSONObject params, final Listener<Track[]> listener) {
        String url = ApiConstant.RELATED;
        GsonRequest<RelatedWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, RelatedWrapper.class, new Response.Listener<RelatedWrapper>() {
            @Override
            public void onResponse(RelatedWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }
    public static void artist(Context context, JSONObject params, final Listener<Track[]> listener) {
        String url = ApiConstant.ARTIST;
        GsonRequest<RelatedWrapper> request = new GsonRequest<>(Request.Method.GET,
                url, params, RelatedWrapper.class, new Response.Listener<RelatedWrapper>() {
            @Override
            public void onResponse(RelatedWrapper response) {
                listener.onSuccess(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        addToQueue(context, request);
    }

}
