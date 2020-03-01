package IR;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class RegAlloc {
    public static void allocate(String outputPath) throws IOException
    {
        Pattern pattern = Pattern.compile("(,|\\s|\\(|\\()");
        int numTempRegs = 0;
        List<TmpReg> listTempRegs = new LinkedList<TmpReg>();
        
        while (true)
        {
            int start = 0, end = 0, lineNum = 1;

            for (String line : Files.readAllLines(Paths.get(outputPath)))
            {
                for (String splited : pattern.split(line))
                {
                    String s = splited;
                    if (splited.contains(")"))
                        s = splited.substring(0, splited.indexOf(")") );
                    if (s.equals("Temp_" + numTempRegs))
                    {
                        if (start == 0)
                            start = lineNum;
                        end = lineNum;
                        break;
                    }
                }
                lineNum++;
            }
            if (start == 0 && end == 0) { break; }
            listTempRegs.add(new TmpReg(numTempRegs, start, end));
            numTempRegs++;
        }

        Graph graph = new Graph(listTempRegs.size());
        for (TmpReg reg1 : listTempRegs)
            for (TmpReg reg2 : listTempRegs)
                if (reg1.isInterfering(reg2) && reg1.id != reg2.id)
                    graph.addEdge(reg1.id, reg2.id);

        HashMap<Integer, Integer> colors = graph.doColor();

        String text = new String(Files.readAllBytes(Paths.get(outputPath)));
        for (int i = numTempRegs; i >= 0; i--)
        {
            Pattern pat = Pattern.compile("Temp_" + i);
            text = pat.matcher(text).replaceAll("\\$t" + colors.get(i));
        }
        Files.write(Paths.get(outputPath), text.getBytes());
    }
}

class TmpReg
{

    int id;
    int start;
    int end;

    public TmpReg(int id, int start, int end)
    {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public boolean isInterfering(TmpReg t)
    {
        int start1 = t.start, end1 = t.end;
        int start2 = this.start, end2 = this.end;

        return (start1 >= start2 && start1 <= end2) ||
               (end1 >= start2 && end1 <= end2)     ||
               (start2 >= start1 && start2 <= end1) ||
               (end2 >= start1 && end2 <= end1)     ;
    }

}


class Graph
{

    private final int numVer;
    private List<Integer> adjList[];

    Graph(int numVer)
    {
        this.numVer = numVer;
        adjList = new LinkedList[numVer];
        for (int i = 0; i < numVer; i++)
            adjList[i] = new LinkedList();
    }

    void addEdge(int v, int w)
    {
        adjList[v].add(w);
        adjList[w].add(v);
    }

    HashMap<Integer, Integer> doColor()
    {
        int result[] = new int[numVer];
        Arrays.fill(result, -1);
        result[0] = 0;

        boolean available[] = new boolean[numVer];

        for (int i = 1; i < numVer; i++)
        {
            Arrays.fill(available, true);

            for (Integer v : adjList[i])
            {
                if (result[v] != -1) { available[result[v]] = false; }
            }
            int color;
            for (color = 0; color < numVer; color++)
                if (available[color])
                    break;
            result[i] = color;
        }

        HashMap<Integer, Integer> colors = new HashMap<Integer, Integer>();
        for (int i = 0; i < numVer; i++)
            colors.put(i, result[i]);

        return colors;
    }

}