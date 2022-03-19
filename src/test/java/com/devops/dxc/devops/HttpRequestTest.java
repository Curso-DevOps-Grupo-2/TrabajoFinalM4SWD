package com.devops.dxc.devops;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.HashMap;
import java.util.Map;

import com.devops.dxc.devops.model.Dxc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCalcula10_AhorroMenor1M() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 10);
        params.put("ahorro", 10);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getDxc()).isEqualTo(10);
        assertThat(result.getBody().getSaldo()).isEqualTo(0);
        params.put("sueldo", 10);
        params.put("ahorro", 1000000);
        result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getDxc()).isEqualTo(1000000);
        assertThat(result.getBody().getSaldo()).isEqualTo(0);
    }

    @Test
    public void testGetCalcula10_AhorroMayor1M_DxcMenor1M() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 10);
        params.put("ahorro", 1000001);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getDxc()).isEqualTo(1000000);
        assertThat(result.getBody().getSaldo()).isEqualTo(1);
        params.put("sueldo", 10);
        params.put("ahorro", 10000000);
        result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getDxc()).isEqualTo(1000000);
        assertThat(result.getBody().getSaldo()).isEqualTo(9000000);
    }

    @Test
    public void testGetCalcula10_DxcSuperaMaximo() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 10);
        params.put("ahorro", 50000000);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getDxc()).isCloseTo(4500000, within(500000));
    }

    @Test
    public void testGetCalcula10_ImpuestoTramo1() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 1000000);
        params.put("ahorro", 10);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getImpuesto()).isEqualTo(0);
    }

    @Test
    public void testGetCalcula10_ImpuestoTramo3() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 1488691);
        params.put("ahorro", 100);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getImpuesto()).isEqualTo(8);
    }

    @Test
    public void testGetCalcula10_ImpuestoTramo4() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 2481151);
        params.put("ahorro", 100);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getImpuesto()).isEqualTo(13);
    }

    @Test
    public void testGetCalcula10_ImpuestoTramo5() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 3466667);
        params.put("ahorro", 100);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getImpuesto()).isEqualTo(23);
    }

    @Test
    public void testGetCalcula10_ImpuestoTramo6() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 4458334);
        params.put("ahorro", 100);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getImpuesto()).isEqualTo(30);
    }

    @Test
    public void testGetCalcula10_ImpuestoTramo7() throws Exception {
        String url = "http://localhost:" + port + "/rest/msdxc/calcula-10?sueldo={sueldo}&ahorro={ahorro}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("sueldo", 5950001);
        params.put("ahorro", 100);
        ResponseEntity<Dxc> result = this.restTemplate.getForEntity(url, Dxc.class, params);
        assertThat(result.getBody().getImpuesto()).isEqualTo(35);
    }
}
