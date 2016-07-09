package com.arisux.airi.lib.client.render;

public class Matrix4
{
    private float[] matrix;
    public static final int size = 4;

    public Matrix4()
    {
        this.matrix = new float[size * size];
        this.setIdentity();
    }

    public Matrix4(float[] arr)
    {
        int n = size * size;

        if (arr.length != n)
        {
            throw new RuntimeException("Matrix4 constructor needs a double array of " + n + " elements");
        }

        matrix = arr;
    }

    public void clone(Matrix4 other)
    {
        matrix = new float[size * size];

        for (int i = 0; i < matrix.length; i++)
        {
            matrix[i] = other.matrix[i];
        }
    }

    public void setIdentity()
    {
        for (int i = 0; i < matrix.length; i++)
        {
            matrix[i] = 0;
        }

        for (int i = 0; i < size; i++)
        {
            matrix[i * size + i] = 1;
        }
    }

    public Matrix4 mul(Matrix4 right)
    {
        Matrix4 result = new Matrix4();
        int idx = 0;
        float r;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                r = 0;

                for (int k = 0; k < size; k++)
                {
                    r += this.matrix[i * size + k] * right.matrix[k * size + j];
                }

                result.matrix[idx] = r;
                idx++;
            }
        }

        return result;
    }

    public float get(int col, int row)
    {
        return matrix[row * 4 + col];
    }

    public float[] transform(float x, float y, float z, float w)
    {
        float[] r = new float[4];
        float[] m = this.matrix;
        int idx = 0;

        for (int i = 0; i < r.length; ++i)
        {
            r[i] = m[idx] * x + m[idx + 1] * y + m[idx + 2] * z + m[idx + 3] * w;
            idx += size;
        }

        return r;
    }

    public String toString()
    {
        String r = "[Matrix4]\n";
        int idx = 0;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                r += " " + matrix[idx++];
            }
            r += "\n";
        }

        return r;
    }
}
