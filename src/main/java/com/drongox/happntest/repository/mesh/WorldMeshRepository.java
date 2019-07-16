package com.drongox.happntest.repository.mesh;

import com.drongox.happntest.object.GeoCoordinate;
import com.drongox.happntest.object.mesh.WorldMesh;
import com.drongox.happntest.object.poi.Poi;
import com.univocity.parsers.tsv.TsvParser;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorldMeshRepository
{
  private final TsvParser tsvParser;
  @Value("${mesh.repo.sourceFile}")
  private String tsvFileName;


  public WorldMesh readMesh()
  {
    Resource tsvFile = new ClassPathResource(tsvFileName);
    try (FileReader fileReader = new FileReader(tsvFile.getFile())) {
      WorldMesh worldMesh = new WorldMesh();
      tsvParser.parseAll(fileReader)
               .stream()
               .skip(1)
               .map(strings -> Poi.of(strings[0],
                                      GeoCoordinate.of(new BigDecimal(strings[1]),
                                                       new BigDecimal(strings[2]))))
               .forEach(worldMesh::addPoi);
      return worldMesh;

    }
    catch (IOException ioException) {
      throw new IllegalStateException("Unable to initialize PoiRepository", ioException);
    }
  }
}
