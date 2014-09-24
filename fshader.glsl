//author Hitesh vyas

//coordinates
varying vec2 co;
//texture
uniform sampler2D texture;

// Light color
uniform vec4 lightColor;

// Diffuse reflection color
uniform vec4 diffuseColor;

//specular color
uniform vec4 specColor;

//specular exponent
uniform float sExponent;

// Vectors "attached" to vertex and get sent to fragment shader
varying vec3 lPos;
varying vec3 vPos;
varying vec3 vNorm;



void main() 
{ 

// calculate your vectors
    vec3 L = normalize (lPos - vPos);
    vec3 N = normalize (vNorm);
    
     // calculate components
    vec4 diffuse = lightColor * diffuseColor * (dot(N, L));
//	  vec4 diffuse = lightColor * diffuseColor;
	//calculate reflection	
	vec3 R=reflect(L,N);
	
	//get the eye vector
	vec3 Eye=normalize(vPos);
	
	//get the specular vector
	vec4 specular= lightColor*specColor*max(pow(dot(R,Eye),sExponent),0.0);
	
	vec4 final=diffuse+specular;
	
	
	
 	 // replace with proper texture function
 	gl_FragColor = texture2D(texture,co)+final;
  
} 
