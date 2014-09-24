
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;

import com.jogamp.opengl.util.texture.*;

public class Parameters {
	IntBuffer buffer;
	BufferedImage myImage = null;
	
	float r = 15f;
	
	// materials / lighting data
	private float lightpos[] = { -2.0f, 3.0f, 4.0f, 1.0f };
	private float lightColor[] = { 1.0f, 1.0f, 0.5f, 1.0f };
	private float diffuse[] = { 0.89f, 0.0f, 0.0f, 1.0f };
	
	// specular color
	private float specularColor[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	// specular exponent
	private float sExponent = 2.0f;
	private float sExponent1 = 6.0f;
	
	float[] eye = { -0.2f, 0.0f, 1.4f };
	float[] look = { -0.3f, 0.0f, 0.0f };
	float[] up = { 0.0f, 1.0f, 0.0f };
	
	// left right top
	float[] lrt = { -1.5f, 1.0f, 1.5f };
	
	// bottom near far
	float[] bnf = { -1.5f, 1.0f, 2.5f };

	/**
	 * constructor
	 */
	public Parameters() {

	}
	
	//set up varibles at shader for H
	public void setUpPhong(int program, GL2 gl2) {

		int location = gl2.glGetUniformLocation(program, "lrt");
		gl2.glUniform3fv(location, 1, lrt, 0);

		location = gl2.glGetUniformLocation(program, "bnf");
		gl2.glUniform3fv(location, 1, bnf, 0);

		// eye coordinates of camera
		location = gl2.glGetUniformLocation(program, "eye");
		gl2.glUniform3fv(location, 1, eye, 0);

		// look coordinates of camera
		location = gl2.glGetUniformLocation(program, "look");
		gl2.glUniform3fv(location, 1, look, 0);

		// up coordinates of camera
		location = gl2.glGetUniformLocation(program, "up");
		gl2.glUniform3fv(location, 1, up, 0);

		// Here's code for the diffuse component.
		int light = gl2.glGetUniformLocation(program, "lightPosition");
		int lightc = gl2.glGetUniformLocation(program, "lightColor");
		int diff = gl2.glGetUniformLocation(program, "diffuseColor");

		gl2.glUniform4fv(light, 1, lightpos, 0);
		gl2.glUniform4fv(lightc, 1, lightColor, 0);
		gl2.glUniform4fv(diff, 1, diffuse, 0);

		// send specular color
		int specLocation = gl2.glGetUniformLocation(program, "specColor");
		gl2.glUniform4fv(specLocation, 1, specularColor, 0);

		// send specular exponent
		int specExpo = gl2.glGetUniformLocation(program, "sExponent");
		gl2.glUniform1f(specExpo, sExponent);
	}
	
	//set up variable for shader for V
	public void setUpPhong1(int program, GL2 gl2) {
		int location = gl2.glGetUniformLocation(program, "lrt");
		gl2.glUniform3fv(location, 1, lrt, 0);

		location = gl2.glGetUniformLocation(program, "bnf");
		gl2.glUniform3fv(location, 1, bnf, 0);

		// eye coordinates of camera
		location = gl2.glGetUniformLocation(program, "eye");
		gl2.glUniform3fv(location, 1, eye, 0);

		// look coordinates of camera
		location = gl2.glGetUniformLocation(program, "look");
		gl2.glUniform3fv(location, 1, look, 0);

		// up coordinates of camera
		location = gl2.glGetUniformLocation(program, "up");
		gl2.glUniform3fv(location, 1, up, 0);

		// Here's code for the diffuse component.
		int light = gl2.glGetUniformLocation(program, "lightPosition");
		int lightc = gl2.glGetUniformLocation(program, "lightColor");
		int diff = gl2.glGetUniformLocation(program, "diffuseColor");

		gl2.glUniform4fv(light, 1, lightpos, 0);
		gl2.glUniform4fv(lightc, 1, lightColor, 0);
		gl2.glUniform4fv(diff, 1, diffuse, 0);

		// send specular color
		int specLocation = gl2.glGetUniformLocation(program, "specColor");
		gl2.glUniform4fv(specLocation, 1, specularColor, 0);

		// send specular exponent
		int specExpo = gl2.glGetUniformLocation(program, "sExponent");
		gl2.glUniform1f(specExpo, sExponent1);
	}

	//pass the values of trasformation
	public void commonValues(int program, GL2 gl2) {

		// scaling parameters
		float[] scale = { 0.1f, 1.0f, 0.3f };
		int location = gl2.glGetUniformLocation(program, "scale");
		gl2.glUniform3fv(location, 1, scale, 0);

		// rotation on z axis
		float xR = 0;
		location = gl2.glGetUniformLocation(program, "xR");
		gl2.glUniform1f(location, xR);

		// rotation on z axis
		float zR = 0;
		location = gl2.glGetUniformLocation(program, "zR");
		gl2.glUniform1f(location, zR);

		// rotation on y axis
		float yR = 0;
		location = gl2.glGetUniformLocation(program, "yR");
		gl2.glUniform1f(location, yR);

		// transformation
		float[] trans = { -0.4f, 0.0f, 0.0f };
		location = gl2.glGetUniformLocation(program, "trans");
		gl2.glUniform3fv(location, 1, trans, 0);

		setUpPhong(program, gl2);

	}

	public void commonValues1(int program, GL2 gl2) {

		// scaling parameters
		float[] scale = { 0.1f, 0.3f, 0.3f };
		int location = gl2.glGetUniformLocation(program, "scale");
		gl2.glUniform3fv(location, 1, scale, 0);

		// rotation on z axis
		float xR = 00;
		location = gl2.glGetUniformLocation(program, "xR");
		gl2.glUniform1f(location, xR);

		// rotation on z axis
		float zR = 90;
		location = gl2.glGetUniformLocation(program, "zR");
		gl2.glUniform1f(location, zR);

		// rotation on y axis
		float yR = 0;
		location = gl2.glGetUniformLocation(program, "yR");
		gl2.glUniform1f(location, yR);

		// transformation
		float[] trans = { -0.3f, 0.0f, 0.0f };
		location = gl2.glGetUniformLocation(program, "trans");
		gl2.glUniform3fv(location, 1, trans, 0);

		setUpPhong(program, gl2);

	}

	public void commonValues2(int program, GL2 gl2) {

		// scaling parameters
		float[] scale = { 0.1f, 1.0f, 0.3f };
		int location = gl2.glGetUniformLocation(program, "scale");
		gl2.glUniform3fv(location, 1, scale, 0);

		// rotation on z axis
		float xR = 0;
		location = gl2.glGetUniformLocation(program, "xR");
		gl2.glUniform1f(location, xR);

		// rotation on z axis
		float zR = 0;
		location = gl2.glGetUniformLocation(program, "zR");
		gl2.glUniform1f(location, zR);

		// rotation on y axis
		float yR = 0;
		location = gl2.glGetUniformLocation(program, "yR");
		gl2.glUniform1f(location, yR);

		// transformation
		float[] trans = { -0.1f, 0.0f, 0.0f };
		location = gl2.glGetUniformLocation(program, "trans");
		gl2.glUniform3fv(location, 1, trans, 0);

		setUpPhong(program, gl2);

	}

	public void commonValues3(int program, GL2 gl2) {

		// scaling parameters
		float[] scale = { 0.1f, 0.8f, 0.3f };
		int location = gl2.glGetUniformLocation(program, "scale");
		gl2.glUniform3fv(location, 1, scale, 0);

		// rotation on z axis
		float xR = 0;
		location = gl2.glGetUniformLocation(program, "xR");
		gl2.glUniform1f(location, xR);

		// rotation on z axis
		float zR = r;
		location = gl2.glGetUniformLocation(program, "zR");
		gl2.glUniform1f(location, zR);

		// rotation on y axis
		float yR = 0;
		location = gl2.glGetUniformLocation(program, "yR");
		gl2.glUniform1f(location, yR);

		// transformation
		float[] trans = { 0.1f, -0.1f, 0.0f };
		location = gl2.glGetUniformLocation(program, "trans");
		gl2.glUniform3fv(location, 1, trans, 0);

		setUpPhong1(program, gl2);

	}

	public void commonValues4(int program, GL2 gl2) {

		// scaling parameters
		float[] scale = { 0.1f, 0.8f, 0.3f };
		int location = gl2.glGetUniformLocation(program, "scale");
		gl2.glUniform3fv(location, 1, scale, 0);

		// rotation on z axis
		float xR = 0;
		location = gl2.glGetUniformLocation(program, "xR");
		gl2.glUniform1f(location, xR);

		// rotation on z axis
		float zR = -r;
		location = gl2.glGetUniformLocation(program, "zR");
		gl2.glUniform1f(location, zR);

		// rotation on y axis
		float yR = 0;
		location = gl2.glGetUniformLocation(program, "yR");
		gl2.glUniform1f(location, yR);

		// transformation
		float[] trans = { 0.4f, -0.1f, 0.0f };
		location = gl2.glGetUniformLocation(program, "trans");
		gl2.glUniform3fv(location, 1, trans, 0);

		setUpPhong1(program, gl2);

	}

	/**
	 * This functions loads texture data to the GPU.
	 * 
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the various shaders.
	 * 
	 * @param filename
	 *            - The name of the texture file.
	 * 
	 */
	public void loadTexture(String filename) {
		// find the image
		File img = new File(filename);
		int pixels[] = null;
		try {
			// read the image
			myImage = ImageIO.read(img);
		} catch (IOException e) {
			System.err.println("Error couldn't read image file");
			System.exit(1);
		}
		// get the dimensions
		int w = myImage.getWidth();
		int h = myImage.getHeight();

		// get the pixel values
		pixels = myImage.getRGB(0, 0, w, h, null, 0, h);
		// set buffer
		buffer = IntBuffer.wrap(pixels);

	}

	/**
	 * This functions sets up the parameters for texture use.
	 * 
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the various shaders.
	 * 
	 * @param program
	 *            - The ID of an OpenGL (GLSL) program to which parameter values
	 *            are to be sent
	 * 
	 * @param gl2
	 *            - GL2 object on which all OpenGL calls are to be made
	 * 
	 */
	public void setUpTextures(int program, GL2 gl2) {
		// get the program
		gl2.glUseProgram(program);

		// load sampler
		int loc = gl2.glGetUniformLocation(program, "texture");

		gl2.glUniform1i(loc, 0);

		// geenrate texture
		gl2.glGenTextures(1, buffer);

		// bind texture
		gl2.glBindTexture(gl2.GL_TEXTURE_2D, loc);

		// set 2 params min filer, mag filter
		gl2.glTexParameteri(gl2.GL_TEXTURE_2D, gl2.GL_TEXTURE_MIN_FILTER,
				gl2.GL_LINEAR);
		gl2.glTexParameteri(gl2.GL_TEXTURE_2D, gl2.GL_TEXTURE_MAG_FILTER,
				gl2.GL_LINEAR);

		// bind these parameters
		gl2.glTexImage2D(gl2.GL_TEXTURE_2D, 0, gl2.GL_RGBA, myImage.getWidth(),
				myImage.getHeight(), 0, gl2.GL_BGRA, gl2.GL_UNSIGNED_BYTE,
				buffer);

		// active texture
		gl2.glActiveTexture(gl2.GL_TEXTURE0 + 0);
	}
}
