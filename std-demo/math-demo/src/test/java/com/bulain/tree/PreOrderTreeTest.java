package com.bulain.tree;

import org.junit.jupiter.api.Test;

public class PreOrderTreeTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new PreOrderTree();
        traverse.traverse(tree);
        System.out.println();
    }

}
