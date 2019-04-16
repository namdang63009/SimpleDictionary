/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namdp.frames;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ASUS
 */
public class IntBSTNode {

    public String key;
    IntBSTNode left, right;

    public IntBSTNode(String k) {
        this(k, null, null);
    }

//    IntBSTNode subtree(int from, int to) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public IntBSTNode(String key, IntBSTNode left, IntBSTNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}

class IntBST {

    IntBSTNode root;

    public IntBST() {
        root = null;
    }

    public String visit(IntBSTNode p) {
        if (p != null) {
            System.out.print(p.key + " ");
        }
        return p.key;
    }

    public void BreathFirst() {
        IntBSTNode p = root;
        Queue<IntBSTNode> queue = new LinkedList();
        queue.offer(p); // enqueue
        while (queue.isEmpty() == false) {
            p = queue.poll(); // dequeue
            visit(p);
            if (p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
        }
    }

    public ArrayList<String> toList() {
        return createOrderedList(root);
    }
    ArrayList<String> list = new ArrayList<>();

    private ArrayList<String> createOrderedList(IntBSTNode node) {

        list.add(node.key);
        if (node.left != null) {
            createOrderedList(node.left);
        }

        if (node.right != null) {
            createOrderedList(node.right);
        }

        return list;
    }

    public void Preorder(IntBSTNode p) {
        if (p != null) {
            visit(p);
            Preorder(p.left);
            Preorder(p.right);
        }
    }

    public void Preorder() {
        Preorder(root);
    }

    public void Inorder(IntBSTNode p, StringBuilder st) {
        if (p != null) {
            Inorder(p.left, st);

            st.append(visit(p));
            Inorder(p.right, st);
        }

    }

    public String Inorder() {
        StringBuilder st = new StringBuilder();
        Inorder(root, st);
        return st.toString();
    }

    public void Postorder(IntBSTNode p) {
        if (p != null) {

            Postorder(p.left);
            Postorder(p.right);
            visit(p);
        }
    }

    public void Postorder() {
        Postorder(root);
    }

    public String Search(String el) {
        if (Search(root, el) == null) {
            return null;
        }
        return Search(root, el);
    }

    public String Search(IntBSTNode p, String el) {

        String result = null;
        while (p != null) {
            if (p.key.contains(el)) {

                result = p.key;
                return p.key;
            } else {
                if (el.compareTo(p.key) <= 0) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
        }

        return result;
    }

    public void Insert(String el) {
        if (root == null) {
            root = new IntBSTNode(el);
            root.key = el;

        }
        recursionInsert(el, this.root);
    }

    public IntBSTNode recursionInsert(String el, IntBSTNode parent) {

        if (parent == null) {
            parent = new IntBSTNode(el);
            parent.key = el;

        } else {
            if (parent.key.compareTo(el) <= 0) {
                parent.right = recursionInsert(el, parent.right);
            } else {
                parent.left = recursionInsert(el, parent.left);
            }
        }
        return parent;
    }

    public void deleteByMerging(String el) {
        IntBSTNode tmp, node, p = root, prev = null;
        while (p != null && !p.key.contains(el)) { // find the node p
            prev = p; // with element el;
            if (p.key.compareTo(el) > 0) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        node = p;
        if (p != null && p.key.contains(el)) {
            if (node.right == null) // node has no right child: its left
            {
                node = node.left; // child (if any) is attached to its 
            } // parent;
            else if (node.left == null) // node has no left child: its right
            {
                node = node.right; // child is attached to its parent;
            } else {  // be ready for merging subtrees;
                tmp = node.left; // 1. move left
                while (tmp.right != null) // 2. and then right as far as
                {
                    tmp = tmp.right; // possible;
                }
                tmp.right
                        = // 3. establish the link between
                        node.right;  // the rightmost node of the left
                // subtree and the right subtree;
                node = node.left; // 4.
            }
            if (p == root) {
                root = node;
            } else if (prev.left == p) {
                prev.left = node;
            } else {
                prev.right = node; // 5.
            }
        } else if (root != null) {
            System.out.println("key " + el + " is not in the tree");
        } else {
            System.out.println("the tree is empty");
        }
    }
}
