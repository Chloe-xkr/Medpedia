<template>
  <v-container>
    <v-sheet height="700" class="text-center" v-show="!kgReady">
      <v-progress-circular
        class="mt-16"
        :size="70"
        :width="7"
        color="purple"
        indeterminate
      ></v-progress-circular>
    </v-sheet>
    <svg id="kg" width="960" height="700" v-show="kgReady"></svg>
  </v-container>
</template>

<script>
import * as d3 from 'd3'
import { ellipseForce } from 'd3-ellipse-force'
export default {
  props: {
    graph: {
      required: true,
    },
  },

  data: () => ({
    kgReady: false,
  }),

  mounted() {
    this.loadKg()
  },

  methods: {
    async loadKg() {
      this.$store.dispatch('article/getKg').then(() => {
        this.initKg()
        this.kgReady = true
      })
    },

    initKg() {
      var svg = d3.select('svg#kg'),
        width = +svg.attr('width'),
        height = +svg.attr('height')

      var color = d3.scaleOrdinal(d3.schemeCategory10)

      var nd
      for (var i = 0; i < this.graph.nodes.length; i++) {
        nd = this.graph.nodes[i]
        nd.rx = nd.title.length * 7
        nd.ry = 12
      }

      var simulation = d3
        .forceSimulation()
        .force(
          'link',
          d3.forceLink().id(function (d) {
            return d.id
          })
        )
        .force('collide', ellipseForce(6, 0.5, 5))
        .force('center', d3.forceCenter(width / 2, height / 2))

      var link = svg
        .append('g')
        .attr('class', 'link')
        .selectAll('line')
        .data(this.graph.links)
        .enter()
        .append('line')
        .attr('stroke-width', function (d) {
          return Math.sqrt(d.value)
        })

      var node = svg
        .append('g')
        .attr('class', 'node')
        .selectAll('ellipse')
        .data(this.graph.nodes)
        .enter()
        .append('ellipse')
        .attr('rx', function (d) {
          return d.rx
        })
        .attr('ry', function (d) {
          return d.ry
        })
        .attr('fill', function (d) {
          return color(d.group)
        })
        .attr('onclick', (d) => `location.href='/entries/${d.id}'`)
        .call(
          d3
            .drag()
            .on('start', dragstarted)
            .on('drag', dragged)
            .on('end', dragended)
        )

      var text = svg
        .append('g')
        .attr('class', 'labels')
        .selectAll('text')
        .data(this.graph.nodes)
        .enter()
        .append('text')
        .attr('dy', 2)
        .attr('text-anchor', 'middle')
        .text(function (d) {
          return d.title
        })
        .attr('fill', 'white')

      simulation.nodes(this.graph.nodes).on('tick', ticked)

      simulation.force('link').links(this.graph.links)

      function ticked() {
        link
          .attr('x1', function (d) {
            return d.source.x
          })
          .attr('y1', function (d) {
            return d.source.y
          })
          .attr('x2', function (d) {
            return d.target.x
          })
          .attr('y2', function (d) {
            return d.target.y
          })

        node
          .attr('cx', function (d) {
            return d.x
          })
          .attr('cy', function (d) {
            return d.y
          })
        text
          .attr('x', function (d) {
            return d.x
          })
          .attr('y', function (d) {
            return d.y
          })
      }

      function dragstarted(d) {
        if (!d3.event.active) simulation.alphaTarget(0.3).restart()
        d.fx = d.x
        d.fy = d.y
      }

      function dragged(d) {
        d.fx = d3.event.x
        d.fy = d3.event.y
      }

      function dragended(d) {
        if (!d3.event.active) simulation.alphaTarget(0)
        d.fx = null
        d.fy = null
      }
    },
  },
}
</script>

<style>
.link line {
  stroke: #999;
  stroke-opacity: 0.6;
}

.labels text {
  pointer-events: none;
  font: 10px sans-serif;
}
</style>
