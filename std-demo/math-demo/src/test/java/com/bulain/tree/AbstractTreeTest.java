package com.bulain.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractTreeTest {

    @Test
    public void testHeight() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new AbstractTree() {
            public void traverse(Node node) {
            }
        };
        int height = traverse.height(tree);
        assertEquals(4, height);
    }

}
