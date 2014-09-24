
import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;

public class TesselationShape extends Shape {
	/**
	 * constructor
	 */
	public TesselationShape() {
	}
	
	//create cube
	public void makeCube(int subdivisions) {

		// step defines the difference
		float step = 1.0f / subdivisions;
		float a, b;
		a = 0f;
		b = 1f;
		/*
		 * this loop builds 2 faces of the cube with z-axis being constant
		 */
		for (float y = -0.5f; y < 0.5f; y = y + step) {
			float z = 0.5f;
			for (float x = -0.5f; x < 0.5f; x = x + step) {

				// next x coordinate
				float x1 = x + step;

				// next y coordinate
				float y1 = y + step;

				// if the next coordinates are out of the limit, update them
				if (x1 > 0.5f) {
					x1 = 0.5f;
				}
				if (y1 > 0.5f) {
					y1 = 0.5f;
				}

				// it adds the front face of the cube(anticlockwise)
				addTriangle(x, y, z, a, a, x1, y1, z, b, a, x, y1, z, b, b);
				addTriangle(x, y, z, b, b, x1, y, z, a, b, x1, y1, z, a, a);

				// these 2 triangle adds the back face of the cube(clockwise)
				addTriangle(x, y1, -z, a, a, x1, y1, -z, b, a, x, y, -z, b, b);
				addTriangle(x1, y1, -z, b, b, x1, y, -z, a, b, x, y, -z, b, b);
			}
		}

		/*
		 * this loop builds 2 faces of the cube with x-axis being constant
		 */
		for (float z = -0.5f; z < 0.5f; z = z + step) {
			float x = 0.5f;
			for (float y = -0.5f; y < 0.5f; y = y + step) {

				// calculate next coordinates
				float z1 = z + step;
				float y1 = y + step;

				// if the next coordinates are out of the limit, update them
				if (z1 > 0.5f) {
					z1 = 0.5f;
				}
				if (y1 > 0.5f) {
					y1 = 0.5f;
				}

				// it adds the front face of the cube(anticlockwise)
				addTriangle(x, y, z, a, a, x, y1, z1, b, a, x, y, z1, b, b);
				addTriangle(x, y, z, b, b, x, y1, z, a, b, x, y1, z1, a, a);

				// these 2 triangle adds the back face of the cube(clockwise)
				addTriangle(-x, y, z1, a, a, -x, y1, z1, b, a, -x, y, z, b, b);
				addTriangle(-x, y1, z1, b, b, -x, y1, z, a, b, -x, y, z, a, a);
			}
		}

		/*
		 * this loop builds 2 faces of the cube with y-axis being constant
		 */
		for (float x = -0.5f; x < 0.5f; x = x + step) {
			float y = 0.5f;
			for (float z = -0.5f; z < 0.5f; z = z + step) {

				// calculate next coordinates
				float x1 = x + step;
				float z1 = z + step;

				// if the next coordinates are out of the limit, update them
				if (x1 > 0.5f) {
					x1 = 0.5f;
				}
				if (z1 > 0.5f) {
					z1 = 0.5f;
				}

				// it adds the front face of the cube(anticlockwise)
				addTriangle(x, y, z, a, a, x1, y, z1, b, a, x1, y, z, b, b);
				addTriangle(x, y, z, b, b, x, y, z1, a, b, x1, y, z1, a, a);

				// these 2 triangle adds the back face of the cube(clockwise)
				addTriangle(x1, -y, z, a, a, x1, -y, z1, b, a, x, -y, z, b, b);
				addTriangle(x1, -y, z1, b, b, x, -y, z1, a, b, x, -y, z, a, a);
			}
		}
	}

	/**
	 * makeDefaultShape - creates a "unit" shape of cube using your
	 * tesselation routines.
	 * 
	 */
	public void makeDefaultShape() {
		makeCube(20);
	}

}
