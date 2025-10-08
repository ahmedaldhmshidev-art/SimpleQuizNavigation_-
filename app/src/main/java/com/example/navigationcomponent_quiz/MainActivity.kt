
package com.example.navigationcomponent_quiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationcomponent_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer :DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout with ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ضبط الحواف بالنسبة للشاشات الحديثة
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //تخزين الid تبع drawer في متغير
        drawer = binding.drawerMenuMainId
        // استدعاء NavController من NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.frg_my_NavHostId) as NavHostFragment
        val navController = navHostFragment.navController
//        NavigationUI.setupWithNavController(binding.navView,navController)

        // ضبط AppBarConfiguration (تحديد الشاشات الأساسية بدون زر الرجوع)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.startFragQuiz)// ضع هنا ID للشاشة الأساسية,
           , drawer // ضع id تبع الdrawer
//            يعني الشاشة رايسية او جذر الذي لم يضهر فيها زر رجوع يضهر فيها drawer
        )
        // ربط الـ ActionBar مع NavController
        setupActionBarWithNavController(navController, appBarConfiguration )
        binding.navView.setupWithNavController(navController)
    }

    // تفعيل زر الرجوع في ActionBar
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.frg_my_NavHostId) as NavHostFragment
        val navController = navHostFragment.navController

        return NavigationUI.navigateUp(navController, drawer) || super.onSupportNavigateUp()

//        navigationUI تربط بين تصميم او toolbar مع navigation لتحكم بالعنوان والازرار من خلال navController
//        navigationUp هي الذي تعمل رجوع حسب  ترتيب تسلس تنقل الصفحات

    }
}

