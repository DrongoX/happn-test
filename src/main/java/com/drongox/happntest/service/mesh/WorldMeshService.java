package com.drongox.happntest.service.mesh;

import com.drongox.happntest.object.GeoCoordinate;
import com.drongox.happntest.object.mesh.MeshArea;
import com.drongox.happntest.repository.mesh.WorldMeshRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WorldMeshService
{
  private final WorldMeshRepository worldMeshRepository;


  public int poiCountOn(GeoCoordinate geoCoordinate)
  {
    return worldMeshRepository.readMesh()
                              .getPoiOn(geoCoordinate)
                              .size();
  }


  public List<MeshArea> findHeaviestAreas(int requestedAmount)
  {
    return worldMeshRepository.readMesh()
                              .findHeaviestAreas(requestedAmount);
  }
}
