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

struct MaterialInfo
{
    vec3 albedo;
    float metallic;

    float roughness;
    float ao;
    vec2 wrap;

    bool isAlbedoMap;
    bool isMetallicMap;
    bool isRoughnessMap;
    bool isAoMap;

    bool isNormalMap;
    bool isHeightMap;
    float heightScale;
};

layout(std140, binding = 2) uniform LightUbo
{
    int lightSize;
    LightInfo[10] lightInfos;
};

layout(std140, binding = 3) uniform MaterialUbo
{
    MaterialInfo materialInfo;
};

void main()
{
    color = vec4(materialInfo.albedo, 1);
    //color = vec4(1,0,0,1);
}