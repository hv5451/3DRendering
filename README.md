**This project renders HV object but can be made to render any object by changing minimal configuration**

HV is made using 5 tesselated objects (cube).

There is one light source but used with specular and diffuse light, and
H, V have different materials (different specular coef)  (see method setupPhong1 and setupPhong in parameters.java)

One texture is used from texture.jpg

All the values for lights, texture, transformations and camera settings (frustrum project used) are sent from parameters.java
(See method commonvalue, commonvalue1, commonvalue2, commonvalue3)

All computations with light, transformation, texture and camera are done in vertex and fragment shader.

**Texture is intentionally mapped to different coorindates and tesselated to make material hard object and animation effect**

----- Files included in submission -------
Texture : texture.jpg
Source Code: MainFile.java (Main method) , Parameters.java, Shader.java, Shape.java and TEsselationShapr.java
Shader: vshader.glsl, fshader.glsl
jar files: 4 default files for java windows (gluegen and jogl)
Original image: artwork.jpg
Resulting Image: screenshot.jpg
