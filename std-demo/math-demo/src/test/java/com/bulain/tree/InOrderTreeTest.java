package com.bulain.tree;

import org.junit.jupiter.api.Test;

public class InOrderTreeTest {

    @Test
    public void testTraverse() {
        Node tree = TreeBuilder.buildTree();
        Tree traverse = new InOrderTreee();
        traverse.traverse(tree);
        System.out.println();
    }

}
