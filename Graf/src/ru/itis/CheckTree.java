package ru.itis;

/**
 * 10.05.2018
 * Graf
 *
 * @author Aleksandrov Andrey
 */
public class CheckTree {
    private boolean matrixAdjacency[][];
    private boolean vertex[];
    private int count = 0;


    void setMatrix(boolean matrix[][]){
        matrixAdjacency = matrix;
        vertex = new boolean[matrix.length];
    }

    boolean checkTree(){
        if(vertex == null){
            return false;
        }
        dfs(0);
        int f = 0;
        return count == vertex.length - 1;
    }

    void dfs(int v) {
        vertex[v] = true;
        boolean isWasLastVertex = false;
        for(int i = 0; i < vertex.length; i++){
            if(matrixAdjacency[v][i]){
                if(vertex[i]){
                    if(!isWasLastVertex)
                        isWasLastVertex = true;
                    else
                        count++;
                }else {
                    count++;
                    dfs(i);
                }
            }
        }
    }


}
