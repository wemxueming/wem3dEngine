#version 440
out vec4 color;

in VS_OUT
{
    vec2 texcoord;
    vec3 fragPos;
    vec3 viewPos;
} vs_in;


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
    LightInfo[MAX_LIGHT] lightInfos;
};

struct MaterialInfo
{
    vec3 albedo;
    float metallic;

    float roughness;
    float ao;
    vec2 wrap;

    float heightScale;
    bool isAlbedoMap;
    bool isMetallicMap;
    bool isRoughnessMap;

    bool isAoMap;
    bool isNormalMap;
    bool isHeightMap;
};

void main()
{
    color = vec4(lightInfos[1].attenuation, 1);
}