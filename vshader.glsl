#version 120
//author Hitesh vyas

attribute vec4 vPosition;

//coordinates from opengl
attribute vec2 vTexCoord;

//coordinates to pass in fragment shader
varying vec2 co;

//angle to rotate from x axis
uniform float xR;

//angle to rotate from z axis
uniform float zR;

//angle to rotate from y axis
uniform float yR;

//left right top
uniform vec3 lrt;

//bottom near far
uniform vec3 bnf;

//scaling parameters
uniform vec3 scale;

//transform parameters
uniform vec3 trans;

//eye coordinates
uniform vec3 eye;

//look coordinates
uniform vec3 look;

//up coordinates
uniform vec3 up;

// Vectors to "attach" to vertex and get sent to fragment shader
// Vectors and points will be passed in "eye" space
varying vec3 lPos;
varying vec3 vPos;
varying vec3 vNorm;

// Light position is given in world space
uniform vec4 lightPosition;

// Normal vector at vertex (in model space)
attribute vec3 vNormal;


void main()
{    

	//transformation matrix initialization
	mat4 t = mat4 (1.0,  0.0,  0.0,  0.0, 
                   0.0,  1.0,  0.0,  0.0,
                   0.0,  0.0,  1.0,  0.0,
                   0.0,  0.0,  0.0,  1.0);
    
    //scaling matrix
    mat4 sca = mat4 (scale.x,  0.0,  0.0,  0.0, 
                   0.0,  scale.y,  0.0,  0.0,
                   0.0,  0.0,  scale.z,  0.0,
                   0.0,  0.0,  0.0,  1.0);
    //update it               
    t=sca*t;
    
    //rotate from x
    mat4 rotX = mat4 (1.0,  0.0,  0.0,  0.0,
    				0.0,cos(radians(xR)),  -sin(radians(xR)),  0.0, 
                    0.0, sin(radians(xR)),  cos(radians(xR)),  0.0,
                   0.0,  0.0,  0.0,  1.0);
    //updates
    rotX=transpose(rotX);
    t=rotX*t;
    

    //rotate from z
    mat4 rotZ = mat4 (cos(radians(zR)),  -sin(radians(zR)),  0.0,  0.0, 
                   sin(radians(zR)),  cos(radians(zR)),  0.0,  0.0,
                   0.0,  0.0,  1.0,  0.0,
                   0.0,  0.0,  0.0,  1.0);
    //updates
    rotZ=transpose(rotZ);
    t=rotZ*t;
    
    //rotate from y
    mat4 rotY = mat4 (cos(radians(yR)),  0.0,  sin(radians(yR)),  0.0, 
                   0.0,  1.0,  0.0,  0.0,
                   -sin(radians(yR)),  0.0,  cos(radians(yR)),  0.0,
                   0.0,  0.0,  0.0,  1.0);
    
    rotY=transpose(rotY);
    t=rotY*t;
    
    //translation matrix
    mat4 tra = mat4 (1.0,  0.0,  0.0,  0.0, 
                   0.0,  1.0,  0.0,  0.0,
                   0.0,  0.0,  1.0,  0.0,
                   trans.x,  trans.y,  trans.z,  1.0);
                   
    t=tra*t;
    
    //--model
    
 //calculations for camera matrix
	vec3 n=eye-look;
	n=normalize(n);
	vec3 u=cross(up,n);
	u=normalize(u);
	vec3 v=cross(n,u);
  	 
  	 //camera matrix
	 mat4 cam = mat4 (u.x,  u.y,  u.z,  dot(-u,eye), 
                      v.x,  v.y,  v.z,  dot(-v,eye), 
                      n.x,  n.y,  n.z,  dot(-n,eye), 
                      0.0,  0.0,  0.0,  1.0);
   	//transpose cam
   	cam=transpose(cam);
    
    
    //--view
    
	//frustrum matrix
	mat4 frus = mat4 (2.0*bnf.y/(lrt.y-lrt.x),  0.0,  0.0,  0.0, 
                      0.0,  2.0*bnf.y/(lrt.z-bnf.x),  0.0,  0.0,
                     (lrt.y+lrt.x)/(lrt.y-lrt.x),  (lrt.z+bnf.x)/(lrt.z-bnf.x),  -(bnf.z+bnf.y)/(bnf.z-bnf.y),  -1.0,
                      0.0,  0.0,  -2.0*bnf.z*bnf.y/(bnf.z-bnf.y),  0.0);
    
	
	//--poj
		
	mat4 modelViewMatrix = cam * tra;
    
    vec4 vertexInEye = modelViewMatrix * vPosition;
    vec4 lightInEye = cam * lightPosition;
    
     vec4 normalInEye = normalize(modelViewMatrix * vec4(vNormal, 0.0));

    // pass our vertex data to the fragment shader
    lPos = lightInEye.xyz;
    vPos = vertexInEye.xyz;
    vNorm = normalInEye.xyz;
	
	
	
	//declaration of new vertex coordinates
	vec4 pos=vPosition;
	
	pos=frus*cam*t*vPosition;
		
	
	//pass the coordinates
	co=vTexCoord;
	
	//set the coordinates
	gl_Position =  pos;


}
