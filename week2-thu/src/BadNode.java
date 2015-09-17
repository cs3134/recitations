/**
 * This is the singly-linked list node class. BAD IDEA. DON'T USE.
 * @author annalawson
 * @param <NodeT> type of data store in node
 */
public class BadNode<NodeT> {
        private NodeT data;
        private BadNode<NodeT> next;

        public BadNode(NodeT d, BadNode<NodeT> n) {
            data = d;
            next = n;
        }

        //Now we need setters and getters
        public NodeT getData() {
            return data;
        }
        public void setData(NodeT d) {
            data = d;
        } 
        public BadNode<NodeT> getNext() {
            return next;
        }
        public void setNext(BadNode<NodeT> n) {
            next = n;
        }
}

