package com.example.nicolas.vercoapp.service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.nicolas.vercoapp.activities.ProductDetailActivity;
import com.example.nicolas.vercoapp.activities.SearchProductActivity;
import com.example.nicolas.vercoapp.adapters.ProductAdapter;
import com.example.nicolas.vercoapp.model.Product;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 18/11/2017.
 */

public class ProductService {
    public Integer getDiscountPercent(Double discount){
        return (int)(discount*100);
    }
    public Double getPriceDiscount(Double discountPercent, Double price){
        return (double)Math.round(price*(1 - discountPercent));
    }
    public String getNameFilterType(int filterType){
        System.out.print("Tipo de Filtro"+filterType);
        String filterString="";
        switch (filterType) {
            case 0:
                filterString="hombre";
                break;
            case 1:
                filterString="mujer";
                break;
            case 2:
                filterString="mostrar todo";
                break;
        }
        return filterString;
    }

    public List<Product> getListSearchProduct(String queryEntered, String filterSelected) {
        final List<Product> productList = new ArrayList<Product>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");
        query.whereContains("labels", queryEntered);
        if (!"mostrar todo".equalsIgnoreCase(filterSelected)) {
            query.whereEqualTo("sex", filterSelected);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        for (final ParseObject product : objects) {
                            final String trademark = product.getString("trademark");
                            final String model = product.getString("model");
                            final Double price = product.getDouble("price");
                            final Double discount = product.getDouble("discount");
                            final String sex = product.getString("sex");
                            final String type = product.getString("type");
                            final String labels = product.getString("labels");
                            //**********get bitmap from parse file
                            ParseFile photo = product.getParseFile("image");
                            if (photo == null) {
                                return;
                            }
                            photo.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        if (data.length == 0) {
                                            return;
                                        }
                                        Bitmap photoBmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        productList.add(new Product(trademark, model, price, discount, sex, type, labels, photoBmp));
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
        return productList;
    }
}
