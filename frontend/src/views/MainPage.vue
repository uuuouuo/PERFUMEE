<template>
  <div>
    <v-carousel
      cycle
      height="600"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(item, i) in items" :src="item.src" :key="i">
        <v-row class="fill-height" align="center" justify="center">
          <div class="text-h2">{{ item.text }}</div>
        </v-row>
      </v-carousel-item>
    </v-carousel>
    <male-perfume></male-perfume>
    <female-perfume></female-perfume>
  </div>
</template>

<script>
import MalePerfume from "../components/perfume/MalePerfume.vue";
import FemalePerfume from "../components/perfume/FemalePerfume.vue";
export default {
  components: {
    MalePerfume,
    FemalePerfume,
  },
  data() {
    return {
      model: 0,
      items: [
        {
          src: "https://cdn.pixabay.com/photo/2019/04/06/19/22/glass-4108085_960_720.jpg",
          text: "1번",
        },
        {
          src: "https://cdn.pixabay.com/photo/2016/06/15/22/22/still-life-1460067_960_720.jpg",
          text: "two",
        },
        {
          src: "https://cdn.pixabay.com/photo/2018/01/30/14/22/lavender-3118882_960_720.jpg",
          text: "三",
        },
        {
          src: "https://cdn.pixabay.com/photo/2018/08/20/19/38/fragrance-3619762_960_720.jpg",
          text: "넷",
        },
        {
          src: "https://cdn.pixabay.com/photo/2019/11/29/10/03/perfume-4660970_960_720.jpg",
          text: "5",
        },
      ],
    };
  },
};
</script>
