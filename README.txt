Approach to the problem 

Do MST from start to end using prim's algorihtm. Since edges are enqueue in increasing weight.
We can be assured that once we hit target, the highest weight among all edges traversed so far
is the max effort rating of the easiest path.

Algorithm:

1) Fill boolean array to indicate visited - O(v)

2) Enqueue start vertex onto priority queue. Traverse using Prim's MST while updating max if necessary. 
   Only difference is once we hit target, we stop and return max. - O(ElogE)

