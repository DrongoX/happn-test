package com.drongox.happntest.controller.mesh;

import com.drongox.happntest.object.GeoCoordinate;
import com.drongox.happntest.object.mesh.MeshArea;
import com.drongox.happntest.service.mesh.WorldMeshService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/worldmesh")
public class WorldMeshController
{
  private final WorldMeshService worldMeshService;


  @GetMapping(path = "/poicount")
  public Integer poiCountOn(@RequestParam BigDecimal min_lat, @RequestParam BigDecimal min_lon)
  {
    return worldMeshService.poiCountOn(GeoCoordinate.of(min_lat, min_lon));
  }


  @GetMapping(path = "/heaviest")
  public List<MeshArea> topWeight(@RequestParam int amount)
  {
    return worldMeshService.findHeaviestAreas(amount);
  }
}
