package sunny.koranpagi.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {

    companion object {
        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if (instance == null) {
                instance = DBHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable("News", true,
                "id" to INTEGER + AUTOINCREMENT + PRIMARY_KEY + UNIQUE,
                "url_photo" to TEXT,
                "url_news" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable("User", true)
    }

    fun insertDB(db: SQLiteDatabase, name: String, email: String) {
        db.insert("User",
                "name" to name,
                "email" to email
        )
    }


}

// Access property for Context
val Context.database: DBHelper
    get() = DBHelper.getInstance(getApplicationContext())
