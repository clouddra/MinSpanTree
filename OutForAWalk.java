import java.util.*;

// A0072292H
// Chong Yun Long

class OutForAWalk {
	private int V; // number of vertices in the graph (number of rooms in the building)
	private int StartingPoint; // Grace is now in this point
	private int TargetPoint; // Grace wants to go to this point
	private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	// --------------------------------------------
	private PriorityQueue<IntegerPair> q ; 
	private Vector<Boolean> visited ; 

	// --------------------------------------------

	public OutForAWalk() {}

	int Query() {
		if (StartingPoint==TargetPoint) return 0 ; // if equal start==end don't search
		
		q = new PriorityQueue<IntegerPair>() ;
		visited = new Vector<Boolean>() ;
		visited.setSize(V) ;
		Collections.fill(visited, false) ; // initially all not visited
		
		visited.set(StartingPoint, true) ;		
		for (int i=0 ; i < AdjList.get(StartingPoint).size() ; i++)
			q.offer(AdjList.get(StartingPoint).get(i)) ;	// push all edges of start point
		
		return effort(); 	
	}

	// You can add extra function if needed
	// --------------------------------------------
	int effort() {
		int max = -1;
		int next;
		IntegerPair current;
		while (!q.isEmpty()) {

			// pop until we get an unvisited vertex
			for (current = q.poll(); visited.get(current.first()); current = q.poll());

			max = Math.max(current.second(), max);
			next = current.first();
			visited.set(next, true);

			if (next == TargetPoint) // return once we reach target
				return max;
			
			else 				// push edges of the next vertex
				for (int i = 0; i < AdjList.get(next).size(); i++)
					// optional: enqueue only unvisited vertex (makes the queue smaller)
					if (!visited.get(AdjList.get(next).get(i).first()))
						q.offer(AdjList.get(next).get(i));
		}
		return 0;
	}
	

	// --------------------------------------------

	void run() {
		// do not alter this method
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			V = sc.nextInt(); StartingPoint = sc.nextInt(); TargetPoint = sc.nextInt();

			// clear the graph and read in a new graph as Adjacency List
			AdjList = new Vector < Vector < IntegerPair > >();
			for (int i = 0; i < V; i++) {
				AdjList.add(new Vector<IntegerPair>());

				int k = sc.nextInt();
				while (k-- > 0) {
					int j = sc.nextInt(), w = sc.nextInt();
					AdjList.get(i).add(new IntegerPair(j, w)); // edge (corridor) weight (effort rating) is stored here
				}
			}

			System.out.println(Query());
		}
	}

	public static void main(String[] args) {
		// do not alter this method
		OutForAWalk ps4 = new OutForAWalk();
		ps4.run();
	}
}
