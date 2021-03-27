package org.example.osm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import lombok.RequiredArgsConstructor;
import org.example.Main;
import org.example.data.dto.NodeRepository;
import org.example.genearated.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OSMFillerService {
    private static final Logger log = LoggerFactory.getLogger("OSMFillerService");

    private final NodeRepository nodeRepository;

    private final OSMFillerConfiguration configuration;

    @PostConstruct
    public void fillDatabase() throws XMLStreamException, IOException, JAXBException {
        InputStream archivedStream = Main.class.getClassLoader().getResourceAsStream("RU-NVS.osm.bz2");
        OsmUnpackDecorator unpackedStream = new OsmUnpackDecorator(archivedStream);
        OsmXMLProcessor xmlProcessor = new OsmXMLProcessor(unpackedStream);
        List<Node> parsedNodes;
        List<org.example.data.Node> converted = new ArrayList<>();
        log.info("Started parsing...");
        if (!(parsedNodes = xmlProcessor.unmarshalNextNodes(configuration.getNodes())).isEmpty()) {
            for (Node parsed : parsedNodes) {
                converted.add(org.example.data.Node.fromGenerated(parsed));
            }
            nodeRepository.saveAll(converted);
            converted.clear();
        }
    }
}
