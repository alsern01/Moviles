using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace MazesAndMore
{
    public class GameManager : MonoBehaviour
    {

#if UNITY_EDITOR
        public int levelToPlay;
#endif
        public LevelManager levelManager;

        // Start is called before the first frame update
        void Start()
        {
            if (_instance != null)
            {
                _instance.levelManager = levelManager;
                DestroyImmediate(gameObject);
                return;
            }
        }

        // Update is called once per frame
        void Update()
        {

        }

        private static GameManager _instance;

        private void StartNewSecene()
        {
            if (levelManager)
            {
                //...
            }
        }
    }
}
