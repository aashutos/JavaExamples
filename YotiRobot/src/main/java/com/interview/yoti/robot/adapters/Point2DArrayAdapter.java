package com.interview.yoti.robot.adapters;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.interview.yoti.robot.model.Point2D;
import static com.interview.yoti.robot.operations.Point2DOperation.*;
/**
 *  @author Aashutos Kakshepati
 */
public class Point2DArrayAdapter extends TypeAdapter<Point2D[]> {

	@Override
	public void write(JsonWriter out, Point2D[] values) throws IOException {
		if (values == null) {
			return;
		}
		
		out.beginArray();
		out.value(generatePersistableArrayValues(values));		
		out.endArray();
	}

	@Override
	public Point2D[] read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL || reader.peek() == JsonToken.BEGIN_OBJECT) {
           reader.nextNull();
           return null;
        }
        
        	reader.beginArray();

        	List<Point2D> points = new LinkedList<>();
        	
        	while (reader.peek() == JsonToken.BEGIN_ARRAY) {
	        	reader.beginArray();
	        	Point2D point = new Point2D(reader.nextInt(),reader.nextInt());
	        	reader.endArray();
	        	points.add(point);
        	}
        	reader.endArray();

        
		return points.toArray(new Point2D[points.size()]);
	}

}
