import algorithms.BackPropagation;
import dataManagement.Example;
import dataManagement.Examples;
import network_components.Network;
import plot.Plot;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Network network = new Network(1,1,2,50);
        Examples examples = new Examples("data.txt");

        List<Double> errorX = new ArrayList<>();
        List<Double> errorY = new ArrayList<>();

        double learningRate = 0.01;
        double momentum = 0;

        for(int i = 0; i < 100000; i++) {
            errorX.add(Double.valueOf(Integer.toString(i)));
            Example e = examples.getRandom();
            Double d = BackPropagation.train(network, e.getInputs(), e.getOutputs(), learningRate, momentum).get(0);
            errorY.add(d);
            System.out.println(d);
            //if(i > 100) learningRate *= 0.96;
            //System.out.println("lr" + learningRate);
        }

        List<Double> inputs = new ArrayList<>();
        inputs.add(9.0);

        System.out.println(network.work(inputs));

        //List
        List<Double> x = new ArrayList<>();
        List<Double> spqr = new ArrayList<>();
        List<Double> networkSPQR = new ArrayList<>();
        for (int i=1;i<101;i++) {
            x.add(Double.valueOf(Integer.toString(i)));
        }
        for (int i=0;i<100;i++) {
            spqr.add(Math.sqrt(x.get(i)));
            List<Double> tmp = new ArrayList<>();
            tmp.add(x.get(i));
            networkSPQR.add(network.work(tmp).get(0));
            tmp.clear();
        }

        //Plot Drawing
        Plot plot = Plot.plot(null)
                .yAxis("Błąd", null)
                .xAxis("Epoki", null)
                .series("Błąd", Plot.data().xy(errorX, errorY), null);

        Plot plot2 = Plot.plot(Plot.plotOpts().legend(Plot.LegendFormat.RIGHT))
                .series("SPQR", Plot.data().xy(x, spqr), Plot.seriesOpts().color(Color.BLACK))
                .series("NetworkSPQR", Plot.data().xy(x, networkSPQR), Plot.seriesOpts().color(Color.MAGENTA));
        try {
            plot.save("ErrorPlot", "png");
            plot2.save("ResultPlot", "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
