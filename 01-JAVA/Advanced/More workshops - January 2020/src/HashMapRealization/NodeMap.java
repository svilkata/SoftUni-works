package HashMapRealization;


public class NodeMap {
    private NodeList[] buckets;
    private static final int INITIAL_CAPACITY = 4;
    private static final double LOAD_FACTOR = 0.75;
    private int size;

    NodeMap() {
        this.buckets = new NodeList[INITIAL_CAPACITY];
        this.size = 0;
    }


    public void put(String key, String value) {
        resizeWhenNeeded();

        // get bucket for key
        int bucketIndex = getBucketIndex(key);

        //if this bucket does not exist, initialize it with an empty list
        if (this.buckets[bucketIndex] == null) {
            this.buckets[bucketIndex] = new NodeList();
        }

        //get the bucket where our element should be added
        NodeList currentBucket = this.buckets[bucketIndex];

        Node existing = currentBucket.getFirstElementWithKey(key);
        //there is no element with this key
        if (existing == null) {
            //increase size only when element is unique
            this.size++;

            // create object of type 'Node' with key and value
            Node nodeToAdd = new Node(key, value);

            //add the element to the current bucket
            currentBucket.add(nodeToAdd);
            return;
        }

        currentBucket.overrideElementWithKey(key, value);
//        existing.value = value;
    }

    public boolean contains(String key){
        return this.get(key) != null;
    }

    private void resizeWhenNeeded() {
        //TO DO
//        if (this.size > buckets.length * LOAD_FACTOR) {
//            return;
//        }
//
//        NodeList[] newBuckets = new NodeList[buckets.length * 2];
//        for (NodeList existingList: buckets){
//
//        }
    }

    public int getSize() {
        return this.size;
    }

    public String get(String key) {
        int bucketIndex = getBucketIndex(key);

        NodeList currentBucket = buckets[bucketIndex];
        if (currentBucket == null) {
            return null;
        }

        Node foundNodeInCurrentBucket = currentBucket.getFirstElementWithKey(key);
        return foundNodeInCurrentBucket.value;
    }

    private int getBucketIndex(String key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
}
