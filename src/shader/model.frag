#version 440
out vec4 color;

struct LightInfo
{
    vec3 color;
    float brightness;
    vec3 position;
    float cutoff;
    vec3 direction;
    float outcutoff;
    vec3 attenuation;
};

layout(std140, binding = 2) uniform lightUbo
{
    int lightSize;
    LightInfo[10] lightInfos;
};

void main()
{
    color = vec4(lightInfos[0].direction, 1);
    //color = vec4(1,0,0,1);
}