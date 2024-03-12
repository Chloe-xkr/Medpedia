package cn.edu.zju.sem.g4.taijian;

import cn.edu.zju.sem.g4.taijian.dao.ArticleRepository;
import cn.edu.zju.sem.g4.taijian.entity.AppUser;
import cn.edu.zju.sem.g4.taijian.entity.Article;
import cn.edu.zju.sem.g4.taijian.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Initializes the database (for development).
 */
@Configuration
class LoadDatabase {

  public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

//  @Bean
//  CommandLineRunner initDatabase(ArticleRepository repository) {
//    return args -> {
//      log.info("Preloading " + repository.save(new Article("青光眼", "disease",
//          "<p>青光眼的定义一直在更新，早期人们认为眼压高，就是青光眼，而后来发现部分青光眼患者眼压却是正常的。所以教科书上关于青光眼的定义随着人们对这个疾病认识的提高而变化着。目前归类为青光眼的成因主要来自“眼内压过高”及“视神经脆弱”两个因素。病理是视神经为眼球最脆弱的部位，一旦眼球的压力变大，就会往后面的视神经压迫，造成视神经伤害，最后导致青光眼。</p><p>原发性青光眼通常成因不明，且可能一出生就带有病因，青光眼确实成因可以说是医学上最大谜团之一，尤其是原发性。续发性则和外伤、眼内发炎、眼睛手术、糖尿病等血管疾病及使用类固醇等多重原因有关。</p><p>例如有一种续发性急性青光眼是由于白内障过于成熟、挤压眼内空间，加上气候骤降眼内微血管收缩，造成水晶体肿大，压到眼内排水途径，水分排不出，眼压急速升高而导致急性青光眼发作。统计上有意义的数据表明，身材娇小、远视、年逾55岁的女性有较大青光眼罹患率，身材娇小者相对眼轴也较短是理由之一。</p>")));
//    };
//  }
//  @Bean
//  CommandLineRunner run(AppUserService appUserService){
//    return args -> {
//      appUserService.create(new AppUser("USER_TREST","email",1,"123456"));
//      appUserService.create(new AppUser("USER_TREST2","email",1,"123456"));
//      appUserService.create(new AppUser("USER_TREST3","email",1,"123456"));
//      appUserService.create(new AppUser("USER_TREST4","email",1,"123456"));
//    };
//  }
}
