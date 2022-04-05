package com.ssafy.perfumee.model.dto.recom;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Notes {
  String userId;
  ArrayList<String> notes_names;

}
