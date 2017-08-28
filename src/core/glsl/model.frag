#version 440
out vec4 color;

in VS_OUT
{
    vec2 texcoord;
    vec3 fragPos;
    vec3 viewPos;
} vs_in;

void main()
{
    color = vec4(1);
}