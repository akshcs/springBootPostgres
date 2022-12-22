package com.example.EarthQuakes.service.impl;

import com.example.EarthQuakes.model.EarthQuake;
import com.example.EarthQuakes.service.intf.EarthQuakeParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class EarthQuakeParserImpl implements EarthQuakeParser {
    @Override
    public List<EarthQuake> getEarthQuakes(String source) {
        List<EarthQuake> allQuakes = new ArrayList<>();
        parseNodeList(allQuakes, parseToNodeList(source, initializeParser()), source);
        return allQuakes;
    }

    private void parseNodeList(List<EarthQuake> allQuakes, NodeList nodeList, String source) {
        IntStream.range(0, nodeList.getLength()).boxed().map(nodeList::item).forEach(node -> {
            parseNode(allQuakes, node, source);
        });
    }

    private void parseNode(List<EarthQuake> allQuakes, Node node, String source){
        if (nodeHasQuakeData(node)) {
            Element elem = (Element) node;
            allQuakes.add(getQuakeFromElement(elem, source));
        }
    }

    private EarthQuake getQuakeFromElement(Element elem, String source){
        double mag = getMagnitudeFromElement(elem);
        String title = getTitleFromElement(elem);
        double depth = getDepthFromElement(elem);
        double lat = getLatitudeFromElement(elem);
        double lon = getLongitudeFromElement(elem);
        return new EarthQuake(lat,lon,title,depth,mag,source);
    }

    private boolean nodeHasQuakeData(Node node){
        return node.getNodeName().equals("entry");
    }

    private String getTitleFromElement(Element elem){
        return getTitleFromNodeList(elem.getElementsByTagName("title"));
    }

    private double getMagnitudeFromElement(Element elem){
        return getMagnitudeFromNodeList(elem.getElementsByTagName("title"));
    }

    private double getDepthFromElement(Element elem){
        return getDepthFromNodeList(elem.getElementsByTagName("georss:elev"));
    }

    private double getLatitudeFromElement(Element elem){
        return getLatitudeFromNodeList(elem.getElementsByTagName("georss:point"));
    }

    private double getLongitudeFromElement(Element elem){
        return getLongitudeFromNodeList(elem.getElementsByTagName("georss:point"));
    }

    private String getTitleFromNodeList(NodeList nodeList){
        String titleString = geStringFromNodeList(nodeList);
        if(Objects.nonNull(titleString)){
           return getTitleFromTitleString(titleString);
        }
        return "NO INFORMATION";
    }

    private double getMagnitudeFromNodeList(NodeList nodeList){
        String magnitudeString = geStringFromNodeList(nodeList);
        if(Objects.nonNull(magnitudeString)){
            return getMagnitudeFromMagnitudeString(magnitudeString);
        }
        return 0.0;
    }

    private double getDepthFromNodeList(NodeList nodeList){
        String depthString = geStringFromNodeList(nodeList);
        if(Objects.nonNull(depthString)){
            return Double.parseDouble(depthString);
        }
        return 0.0;
    }

    private double getLatitudeFromNodeList(NodeList nodeList) {
        String latitudeString = geStringFromNodeList(nodeList);
        if(Objects.nonNull(latitudeString)){
            return Double.parseDouble(latitudeString.split(" ")[0]);
        }
        return 0.0;
    }

    private double getLongitudeFromNodeList(NodeList nodeList) {
        String latitudeString = geStringFromNodeList(nodeList);
        if(Objects.nonNull(latitudeString)){
            return Double.parseDouble(latitudeString.split(" ")[1]);
        }
        return 0.0;
    }

    private String getTitleFromTitleString(String titleString){
        int titleStartIndex = titleString.indexOf(" ",5);
        String title = titleString.substring(titleStartIndex+1);
        if (title.startsWith("-")){
            int pos = title.indexOf(" ");
            title = title.substring(pos+1);
        }
        return title;
    }

    private double getMagnitudeFromMagnitudeString(String magnitudeString){
        String magnitudeData = magnitudeString.substring(2,5);
        if (!magnitudeData.contains("?")) {
            return Double.parseDouble(magnitudeData);
        }
        System.err.println("unknown magnitude in data");
        return 0.0;
    }

    private String geStringFromNodeList(NodeList nodeList){
        if(Objects.nonNull(nodeList)){
            return nodeList.item(0).getChildNodes().item(0).getNodeValue();
        }
        return null;
    }

    private DocumentBuilder initializeParser(){
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("parser configuration exception");
            return null;
        }
    }

    private NodeList parseToNodeList(String source, DocumentBuilder documentBuilder){
        try {
            return documentBuilder.parse(source).getDocumentElement().getChildNodes();
        } catch (SAXException e) {
            System.err.println("sax exception");
        } catch (IOException e) {
            System.err.println("io exception");
        }
        return null;
    }
}
