//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package math3d;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vector4f extends Vector implements Serializable, ReadableVector4f, WritableVector4f {
    public static Vector4f ZERO = new Vector4f(0, 0, 0, 0);
    private static final long serialVersionUID = 1L;
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f() {
    }

    public Vector4f(ReadableVector4f src) {
        this.set(src);
    }

    public Vector4f(float x, float y, float z, float w) {
        this.set(x, y, z, w);
    }

    public Vector4f(float f) {
        this.set(f, f, f, f);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f set(ReadableVector4f src) {
        this.x = src.getX();
        this.y = src.getY();
        this.z = src.getZ();
        this.w = src.getW();
        return this;
    }

    public float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public Vector4f translate(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    public Vector4f add(Vector4f target) {
        this.set(this.x + target.x, this.y + target.y, this.z + target.z, this.w + target.w);
        return this;
    }

    public static Vector4f add(Vector4f left, Vector4f right, Vector4f dest) {
        if (dest == null) {
            return new Vector4f(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
        } else {
            dest.set(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
            return dest;
        }
    }

    public Vector4f sub(Vector4f target) {
        this.set(this.x - target.x, this.y - target.y, this.z - target.z, this.w - target.w);
        return this;
    }

    public static Vector4f sub(Vector4f left, Vector4f right, Vector4f dest) {
        if (dest == null) {
            return new Vector4f(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
        } else {
            dest.set(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
            return dest;
        }
    }

    public Vector4f mul(Vector4f target) {
        this.set(this.x * target.x, this.y * target.y, this.z * target.z, this.w * target.w);
        return this;
    }

    public static Vector4f mul(Vector4f left, Vector4f right, Vector4f dest) {
        if (dest == null) {
            return new Vector4f(left.x * right.x, left.y * right.y, left.z * right.z, left.w * right.w);
        } else {
            dest.set(left.x * right.x, left.y * right.y, left.z * right.z, left.w * right.w);
            return dest;
        }
    }

    public Vector4f div(Vector4f target) {
        this.set(this.x / target.x, this.y / target.y, this.z / target.z, this.w / target.w);
        return this;
    }

    public static Vector4f div(Vector4f left, Vector4f right, Vector4f dest) {
        if (dest == null) {
            return new Vector4f(left.x / right.x, left.y / right.y, left.z / right.z, left.w / right.w);
        } else {
            dest.set(left.x / right.x, left.y / right.y, left.z / right.z, left.w / right.w);
            return dest;
        }
    }

    public Vector4f transform(Matrix4f target) {
        float x = target.m00 * this.x + target.m10 * this.y + target.m20 * this.z + target.m30 * this.w;
        float y = target.m01 * this.x + target.m11 * this.y + target.m21 * this.z + target.m31 * this.w;
        float z = target.m02 * this.x + target.m12 * this.y + target.m22 * this.z + target.m32 * this.w;
        float w = target.m03 * this.x + target.m13 * this.y + target.m23 * this.z + target.m33 * this.w;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public static Vector4f transform(Matrix4f left, Vector4f right, Vector4f dest) {
        if (dest == null) {
            dest = new Vector4f();
        }

        float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z + left.m30 * right.w;
        float y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z + left.m31 * right.w;
        float z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z + left.m32 * right.w;
        float w = left.m03 * right.x + left.m13 * right.y + left.m23 * right.z + left.m33 * right.w;
        dest.x = x;
        dest.y = y;
        dest.z = z;
        dest.w = w;
        return dest;
    }

    public Vector negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
        return this;
    }

    public Vector4f negate(Vector4f dest) {
        if (dest == null) {
            dest = new Vector4f();
        }

        dest.x = -this.x;
        dest.y = -this.y;
        dest.z = -this.z;
        dest.w = -this.w;
        return dest;
    }

    public Vector4f normalize() {
        float l = this.length();
        this.set(this.x / l, this.y / l, this.z / l, this.w / l);
        return this;
    }

    public Vector4f normalize(Vector4f dest) {
        float l = this.length();
        if (dest == null) {
            dest = new Vector4f(this.x / l, this.y / l, this.z / l, this.w / l);
        } else {
            dest.set(this.x / l, this.y / l, this.z / l, this.w / l);
        }

        return dest;
    }

    public static float dot(Vector4f left, Vector4f right) {
        return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
    }

    public static float angle(Vector4f a, Vector4f b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1.0F) {
            dls = -1.0F;
        } else if (dls > 1.0F) {
            dls = 1.0F;
        }

        return (float) Math.acos((double) dls);
    }

    public Vector load(FloatBuffer buf) {
        this.x = buf.get();
        this.y = buf.get();
        this.z = buf.get();
        this.w = buf.get();
        return this;
    }

    public Vector scale(float scale) {
        this.x *= scale;
        this.y *= scale;
        this.z *= scale;
        this.w *= scale;
        return this;
    }

    public Vector store(FloatBuffer buf) {
        buf.put(this.x);
        buf.put(this.y);
        buf.put(this.z);
        buf.put(this.w);
        return this;
    }

    public String toString() {
        return "Vector4f: " + this.x + " " + this.y + " " + this.z + " " + this.w;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final void setX(float x) {
        this.x = x;
    }

    public final void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getZ() {
        return this.z;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getW() {
        return this.w;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Vector4f other = (Vector4f) obj;
            return this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w;
        }
    }
}
