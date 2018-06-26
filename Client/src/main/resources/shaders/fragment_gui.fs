#version 330

in vec2 outTexCoord;
in vec3 mvPos;
out vec4 fragColour;

uniform sampler2D texture_sampler;
uniform vec4 colour;

void main()
{
    fragColour = colour * texture(texture_sampler, outTexCoord);
}
