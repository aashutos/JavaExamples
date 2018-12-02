package com.interview.yoti.robot.adapters;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.interview.yoti.robot.model.Point2D;

/**
 *  @author Aashutos Kakshepati
 */
public class Point2DAdapter extends TypeAdapter<Point2D> {

	@Override
	public void write(JsonWriter out, Point2D value) throws IOException {
		if (value == null) {
			return;
		}
		
		out.beginArray();
		out.value(String.format("%d,%d", value.getX(), value.getY()));
		out.endArray();
	}

	@Override
	public Point2D read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL || reader.peek() == JsonToken.BEGIN_OBJECT) {
           reader.nextNull();
           return null;
        }
        
        reader.beginArray();
        Point2D point = new Point2D(reader.nextInt(),reader.nextInt());
       	reader.endArray();
        
		return point;
	}

}
