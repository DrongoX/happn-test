package com.drongox.happntest.controller.poi;

import com.drongox.happntest.object.GeoCoordinate;
import com.drongox.happntest.object.mesh.MeshArea;
import com.drongox.happntest.service.poi.PoiService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/poi")
public class PoiController
{
  private final PoiService poiService;


  @GetMapping(path = "/count")
  public Integer poiCountOn(@RequestParam BigDecimal min_lat, @RequestParam BigDecimal min_lon)
  {
    return poiService.poiCountOn(GeoCoordinate.of(min_lat, min_lon));
  }


  @GetMapping(path = "/heaviest")
  public List<MeshArea> topWeight(@RequestParam int amount)
  {
    return poiService.findHeaviestAreas(amount);
  }
}
