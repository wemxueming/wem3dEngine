#version 440
layout(location = 0) in vec3 vPosition;
layout(location = 1) in vec2 vTexcoord;
layout(location = 2) in vec3 vNormal;

layout(std140, binding = 0) uniform cameraUbo
{
    mat4 projection;
    mat4 view;
};

layout(std140, binding = 1) uniform modelUbo
{
    mat4 model;
};

uniform vec3 cameraPos;

out VS_OUT
{
    vec2 texcoord;
    vec3 fragPos;
    vec3 viewPos;
} vs_out;

void main()
{
    vec4 worldPos = model * vec4(vPosition, 1);
    gl_Position = projection * view * worldPos;

    vs_out.texcoord = vTexcoord;
    vs_out.fragPos = worldPos.xyz;
    vs_out.viewPos = cameraPos;
}