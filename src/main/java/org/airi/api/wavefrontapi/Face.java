package com.arisux.airi.api.wavefrontapi;

import com.arisux.airi.lib.client.render.Normal;
import com.arisux.airi.lib.client.render.UV;
import com.arisux.airi.lib.client.render.Vertex;

public class Face
{
    public Vertex[] vertex;
    public UV[] uv;
    public Normal normal;
    public int vertexNbr;

    public Face(Vertex[] vertex, UV[] uv, Normal normal)
    {
        this.vertex = vertex;
        this.uv = uv;
        this.normal = normal;
        this.vertexNbr = vertex.length;
    }

    public Normal getNormal()
    {
        return normal;
    }

    public void setNormal(Normal normal)
    {
        this.normal = normal;
    }

    public Vertex[] getVertex()
    {
        return vertex;
    }

    public void setVertex(Vertex[] vertex)
    {
        this.vertex = vertex;
    }

    public UV[] getUV()
    {
        return uv;
    }

    public void setUV(UV[] uv)
    {
        this.uv = uv;
    }

    public int getVertexNbr()
    {
        return vertexNbr;
    }

    public void setVertexNbr(int vertexNbr)
    {
        this.vertexNbr = vertexNbr;
    }
}
