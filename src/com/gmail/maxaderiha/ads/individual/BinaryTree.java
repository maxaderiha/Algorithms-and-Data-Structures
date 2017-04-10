package com.gmail.maxaderiha.ads.individual;

import java.io.*;
import java.util.*;

import static com.gmail.maxaderiha.ads.common.TaskFirst.write;

public class BinaryTree extends Thread {
    public static StringBuilder temp;
    private Node root;
    private static StringBuilder elements;
    private Node rootSemiPath;
    private Node maxCentralNode;
    private static boolean flag;

    public BinaryTree() {
        elements = new StringBuilder("");
        rootSemiPath = new Node(0);
        temp = new StringBuilder("");
        maxCentralNode = new Node(0);
        flag = true;
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }

        Node node = root;
        while (true) {
            if (key < node.key) {
                if (node.left == null) {
                    node.left = new Node(key);
                    return;
                } else {
                    node = node.left;
                }
            } else if (key > node.key) {
                if (node.right == null) {
                    node.right = new Node(key);
                    return;
                } else {
                    node = node.right;
                }
            } else {
                return;
            }
        }
    }

    public Node delete(Node v, int key) {
        if (v == null) {
            return null;
        }

        if (key < v.key) {
            v.left = delete(v.left, key);
            return v;
        } else if (key > v.key) {
            v.right = delete(v.right, key);
            return v;
        }

        if (v.left == null) {
            return v.right;
        } else if (v.right == null) {
            return v.left;
        } else {
            int minKey = findMinKey(v.right).key;
            v.key = minKey;
            v.right = delete(v.right, minKey);
            return v;
        }
    }

    public void placeMark(Node v) {
        if (v != null) {
            placeMark(v.left);
            placeMark(v.right);
            solve(v);
        }
    }

    private void solve(Node v) {
        if (v.right == null && v.left == null) {
            v.h = 0;
            v.msl = 0;
            v.weight = v.key;
        } else if (v.right != null && v.left != null) {
            v.h = Math.max(v.right.h, v.left.h) + 1;
            v.msl = v.right.h + v.left.h + 2;
            if (v.left.h > v.right.h) {
                v.weight = v.key + v.left.weight;
            }
            if (v.left.h < v.right.h) {
                v.weight = v.key + v.right.weight;
            }
            if (v.left.h == v.right.h) {
                v.weight = v.key + Math.max(v.left.weight, v.right.weight);
            }
        } else if (v.right != null && v.left == null) {
            v.h = v.right.h + 1;
            v.msl = v.h;
            v.weight = v.key + v.right.weight;
        } else {
            v.h = v.left.h + 1;
            v.msl = v.h;
            v.weight = v.key + v.left.weight;
        }
        if ((v.msl > rootSemiPath.msl) || (v.msl == rootSemiPath.msl && compareWithKey(v, rootSemiPath))) {
            rootSemiPath = v;
        }
        elements.append("\n" + Integer.toString(v.key) + " h=" + v.h + " msl=" + v.msl + " weight=" + v.weight);
    }

    private boolean compareWithKey(Node v, Node w) {
        if ((v.right != null && v.left != null) && (w.right != null && w.left != null)) {
            if (v.key + v.left.weight + v.right.weight >= w.key + w.left.weight + w.right.weight) {
                return true;
            }
        }
        if ((v.right != null && v.left != null) && (w.right != null && w.left == null)) {
            if (v.key + v.left.weight + v.right.weight >= w.key + w.right.weight) {
                return true;
            }
        }
        if ((v.right != null && v.left != null) && (w.right == null && w.left != null)) {
            if (v.key + v.left.weight + v.right.weight >= w.key + w.left.weight) {
                return true;
            }
        }
        if ((v.right != null && v.left != null) && (w.right == null && w.left == null)) {
            if (v.key + v.left.weight + v.right.weight >= w.key) {
                return true;
            }
        }

        if ((v.right != null && v.left == null) && (w.right != null && w.left != null)) {
            if (v.key + v.right.weight >= w.key + w.left.weight + w.right.weight) {
                return true;
            }
        }
        if ((v.right != null && v.left == null) && (w.right != null && w.left == null)) {
            if (v.key + v.right.weight >= w.key + w.right.weight) {
                return true;
            }
        }
        if ((v.right != null && v.left == null) && (w.right == null && w.left != null)) {
            if (v.key + v.right.weight >= w.key + w.left.weight) {
                return true;
            }
        }
        if ((v.right != null && v.left == null) && (w.right == null && w.left == null)) {
            if (v.key + v.right.weight >= w.key) {
                return true;
            }
        }

        if ((v.right == null && v.left != null) && (w.right != null && w.left != null)) {
            if (v.key + v.left.weight >= w.key + w.left.weight + w.right.weight) {
                return true;
            }
        }
        if ((v.right == null && v.left != null) && (w.right != null && w.left == null)) {
            if (v.key + v.left.weight >= w.key + w.right.weight) {
                return true;
            }
        }
        if ((v.right == null && v.left != null) && (w.right == null && w.left != null)) {
            if (v.key + v.left.weight >= w.key + w.left.weight) {
                return true;
            }
        }
        if ((v.right == null && v.left != null) && (w.right == null && w.left == null)) {
            if (v.key + v.left.weight >= w.key) {
                return true;
            }
        }

        if ((v.right == null && v.left == null) && (w.right != null && w.left != null)) {
            if (v.key >= w.key + w.left.weight + w.right.weight) {
                return true;
            }
        }
        if ((v.right == null && v.left == null) && (w.right != null && w.left == null)) {
            if (v.key >= w.key + w.right.weight) {
                return true;
            }
        }
        if ((v.right == null && v.left == null) && (w.right == null && w.left != null)) {
            if (v.key >= w.key + w.left.weight) {
                return true;
            }
        }
        if ((v.right == null && v.left == null) && (w.right == null && w.left == null)) {
            if (v.key >= w.key) {
                return true;
            }
        }
        return false;
    }

    public void findCenterNode(Node v) {
        boolean f = true;
        int hCentralNode = v.msl / 2;
        maxCentralNode = v;
        if (maxCentralNode.h == hCentralNode) {
            flag = false;
            return;
        }

        if (maxCentralNode.right != null) {
            if (maxCentralNode.right.h == hCentralNode) {
                maxCentralNode = maxCentralNode.right;
                return;
            }
            if (maxCentralNode.right.h > hCentralNode) {
                maxCentralNode = maxCentralNode.right;
                f = false;
            }
        }


        if (f) {
            if (maxCentralNode.left != null) {
                if (maxCentralNode.left.h == hCentralNode) {
                    maxCentralNode = maxCentralNode.left;
                    return;
                }
                if (maxCentralNode.left.h > hCentralNode) {
                    maxCentralNode = maxCentralNode.left;
                }
            }
        }


        for (int i = v.h; i > hCentralNode + 1; i--) {
            if (maxCentralNode.right != null && maxCentralNode.left != null) {
                if (maxCentralNode.right.h > maxCentralNode.left.h) {
                    maxCentralNode = maxCentralNode.right;
                    continue;
                }
                if (maxCentralNode.right.h < maxCentralNode.left.h) {
                    maxCentralNode = maxCentralNode.left;
                    continue;
                }
                if (maxCentralNode.right.h == maxCentralNode.left.h) {
                    if (maxCentralNode.right.weight > maxCentralNode.left.weight) {
                        maxCentralNode = maxCentralNode.right;
                    } else {
                        maxCentralNode = maxCentralNode.left;
                    }
                }
            } else if (maxCentralNode.right != null && maxCentralNode.left == null) {
                maxCentralNode = maxCentralNode.right;
            } else {
                maxCentralNode = maxCentralNode.left;
            }
        }
    }

    public void traversal(Node v) {
        if (v != null) {
            temp.append("\n" + v.key);
            traversal(v.left);
            traversal(v.right);
        }
    }

    private Node findMinKey(Node v) {
        if (v.left != null) {
            return findMinKey(v.left);
        } else {
            return v;
        }
    }

    private void task() {
        int check = rootSemiPath.key;
        if (rootSemiPath.msl % 2 == 0) {
            findCenterNode(rootSemiPath);
            root = delete(root, maxCentralNode.key);
        }
        if (flag) {
            root = delete(root, rootSemiPath.key);
        }

    }

    @Override
    public void run() {
        try {
            Scanner input = new Scanner(new File("in.txt"));
            BinaryTree tree = new BinaryTree();
            int i = 0;
            while (input.hasNextInt()) {
                ++i;
                int node = input.nextInt();
                tree.insert(node);
            }
            tree.placeMark(tree.root);
            tree.task();
            tree.traversal(tree.root);
            write("out.txt", temp.toString().substring(1));
            input.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new Thread(null, new Runnable() {
            public void run() {
                new BinaryTree().run();
            }
        }, "1", 12 * 1024 * 1024).start();
    }
}

class Node {
    public int key;
    public int h;
    public int msl;
    public int weight;
    public Node left;
    public Node right;

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "key=" + key + ", hight=" + h + ", msl=" + msl + ", weight=" + weight + ".";
    }
}
