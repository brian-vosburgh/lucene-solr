package org.apache.lucene.spatial.spatial4j.geo3d;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/** Generic shape.  This describes methods that help GeoAreas figure out
* how they interact with a shape, for the purposes of coming up with a
* set of geo hash values.
*/
public interface GeoShape extends Membership {

    /** Return a sample point that is on the outside edge/boundary of the shape.
     *@return samples of all edge points from distinct edge sections.  Typically one point
     * is returned, but zero or two are also possible.
     */
    public GeoPoint[] getEdgePoints();
    
    /** Assess whether a plane, within the provided bounds, intersects
     * with the shape.  Note well that this method is allowed to return "true"
     * if there are internal edges of a composite shape which intersect the plane.  
     * Doing this can cause getRelationship() for most GeoBBox shapes to return
     * OVERLAPS rather than the more correct CONTAINS, but that cannot be
     * helped for some complex shapes that are built out of overlapping parts.
     *@param plane is the plane to assess for intersection with the shape's edges or
     *  bounding curves.
     *@param bounds are a set of bounds that define an area that an
     *  intersection must be within in order to qualify (provided by a GeoArea).
     *@return true if there's such an intersection, false if not.
     */
    public boolean intersects(final Plane plane, final Membership... bounds);

    /** Compute longitude/latitude bounds for the shape.
    *@param bounds is the optional input bounds object.  If this is null,
    * a bounds object will be created.  Otherwise, the input object will be modified.
    *@return a Bounds object describing the shape's bounds.  If the bounds cannot
    * be computed, then return a Bounds object with noLongitudeBound,
    * noTopLatitudeBound, and noBottomLatitudeBound.
    */
    public Bounds getBounds(final Bounds bounds);

    /** Equals */
    public boolean equals(Object o);
}
