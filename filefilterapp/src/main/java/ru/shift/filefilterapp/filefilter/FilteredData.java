package ru.shift.filefilterapp.filefilter;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.constant.LineType;
import ru.shift.filefilterapp.statistic.Addable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class FilteredData implements Addable {

  private final Map<LineType, List<Object>> data;

  public FilteredData() {
    this.data = Arrays.stream(LineType.values())
        .collect(() -> new EnumMap<>(LineType.class),
            (map, type) -> map.put(type, new ArrayList<>()),
            Map::putAll);
  }

  @Override
  public void addInteger(long value) {
    add(value, LineType.INTEGER);
  }

  @Override
  public void addFloat(double value) {
    add(value, LineType.FLOAT);
  }

  @Override
  public void addString(String value) {
    add(value, LineType.STRING);
  }

  private <T> void add(T value, LineType type) {
    data.get(type).add(value);
  }
}
