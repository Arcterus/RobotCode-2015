package RobotCode2015.util;

import RobotCode2015.util.MathUtil;

/**
 * Represents a three-dimensional vector 
 * @author Manan Shah
 */
public class Vector3D
{
    /**
     * The X-position of the point.
     * This represents movement along the axis from one alliance side of the
     * field towards the other alliance side of the field.
     */
    private double X;

    /**
     * The Y-position of the point.
     * This represents movement along the axis from the sides of the field,
     * parallel to the alliance walls and game objectives.
     */
    private double Y;

    /**
     * The Z-position of the point.
     * This represents movement up and down in the direction of gravity by the
     * Earth.  Zero represents a ground-level object, while an increasing value
     * would represent moving up 
     */
    private double Z;
	
    /**
     * Initializes a zero Vector3D
     */

    /**
     * Initializes a new Vector3D with the given coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector3D(double x, double y, double z) 
    { 
	this.X = x; 
	this.Y = y; 
	this.Z = z; 
    }
    
    /** 
     * Default no-args constructor
     */
    public Vector3D() 
    { 
        this(0.0, 0.0, 0.0);
    }
    
    /**
     * Constructs a 3D vector (with Z = 0) 
     * @param magnitude the magnitude of the vector
     * @param direction the direction of the vector
     */
    public Vector3D(double magnitude, double direction)
    {
        this(RobotCode2015.util.MathUtil.cos(direction)*magnitude,
             RobotCode2015.util.MathUtil.sin(direction)*magnitude,
             0);
    }
    
    /** 
     * Adds this Vector3D with another specified Vector3D
     * @param v the vector to add
     * @return a new Vector3D that is the sum of the two vectors
     */
    public Vector3D add(Vector3D v)
    {
        return new Vector3D(this.X + v.X, this.Y + v.Y, this.Z + v.Z);
    }
    
    /**
     * Subtracts this Vector3D with another specified Vector3D
     * @param v the vector to subtract from this vector 
     * @return a new Vector3D that is the difference of the two vectors
     */
    public Vector3D subtract(Vector3D v)
    {
        return new Vector3D(this.X - v.X, this.Y - v.Y, this.Z - v.Z);
    }
    
    /**
     * Scales the given vector by performing scalar multiplication
     * @param d the value with which to scale this vector
     * @return a new Vector that is the original vector scaled by value d
     */
    public Vector3D scale(double d)
    {
        return new Vector3D(this.X*d, this.Y*d, this.Z*d);
    }
    
    /**
     * Gets the magnitude of this vector
     * @return the magnitude of this vector
     */
    public double magnitude()
    {
        return Math.sqrt(X * X + Y * Y + Z * Z);
    }
    
    /**
     * Gets the norm of this vector through (V / |V|)
     * @return 
     */
    public Vector3D normalize() 
    {
        double mag = magnitude();
        return new Vector3D(
            X / mag,
            Y / mag,
            Z / mag
        );
    }
    
    /**
     * Calculates the dot product of this vector with the given vector
     * @param v the other vector
     * @return the dot product of the two vectors
     */
    public double dot(Vector3D v)
    {
        return X * v.X + Y * v.Y + Z * v.Z;
    }
    
    /**
     * Returns the cross product of this vector with the given vector
     * @param v the other vector
     * @return the cross product of the two vectors
     */
    public Vector3D cross(Vector3D v)
    {
        /* a cross b formula:
         * X = a2b3 - a3b2
         * Y = a3b1 - a1b3
         * Z = a1b2 - a2b1
         */
        
        double x = this.Y*v.Z - this.Z*v.Y;
        double y = this.Z*v.X - this.X*v.Z;
        double z = this.X*v.Y - this.Y*v.X;
        
        return new Vector3D(x,y,z);
    }
    
    /**
     * Returns the scalar triple of three vectors
     * @param v1 the first vector
     * @param v2 the second vector
     * @param v3 the third vector
     * @return the scalar triple
     */
    public double scalTrip(Vector3D v1, Vector3D v2, Vector3D v3)
    {
        //v1 dot (v2 cross v3)
        return v1.dot(v2.cross(v3));
    }
    
    /**
     * Returns the vector triple of three vectors
     * @param v1 the first vector
     * @param v2 the second vector
     * @param v3 the third vector
     * @return the vector triple
     */
    public Vector3D vectorTrip(Vector3D v1, Vector3D v2, Vector3D v3)
    {
        return v1.cross(v2.cross(v3));
    }
    
    /**
     * 
     * @return 
     */
    public double direction() {
        double theta = MathUtil.aTan(Y/X);
        if (X < 0) {
            theta = Math.PI - theta;
        }
        return theta;
    }
	
    /**
     * Returns a string representation of the Vector3D
     * @return 
     */
    public String toString() 
    {
        return "<" + 
               IOUtil.padRight(Double.toString(X), 6).substring(0, 5) + ", " + 
               IOUtil.padRight(Double.toString(Y), 6).substring(0, 5) + ", " + 
               IOUtil.padRight(Double.toString(Z), 6).substring(0, 5) + ">";
    }
}
